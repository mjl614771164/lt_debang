package com.leting.common.filter;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.leting.util.Authcode3;

import net.sf.json.JSONObject;
/**
 * 整个项目过滤器，打印相关请求日志
 * @author Administrator
 *
 */
public class CommonFilter implements Filter {
	private static Logger logger = Logger.getLogger(CommonFilter.class);
	
	private static String cookieKeySecret = "";
	private static String cookieValSecret = "";
	private static String userCookieKey = "";
	
	public static List<String> browserList = new ArrayList<String>();
	//browserList里面的值表示的是移动端（非PC端）
	static {
		browserList.add("nokia");
		browserList.add("samsung");
		browserList.add("midp-2");
		browserList.add("cldc1.1");
		browserList.add("symbianos");
		browserList.add("maui");
		browserList.add("untrusted/1.0");
		browserList.add("windows ce");
		browserList.add("iphone");
		browserList.add("ipad");
		browserList.add("android");
		browserList.add("blackberry");
		browserList.add("ucweb");
		browserList.add("brew");
		browserList.add("j2me");
		browserList.add("yulong");
		browserList.add("coolpad");
		browserList.add("tianyu");
		browserList.add("ty-");
		browserList.add("k-touch");
		browserList.add("haier");
		browserList.add("dopod");
		browserList.add("lenovo");
		browserList.add("mobile");
		browserList.add("huaqin");
		browserList.add("aigo-");
		browserList.add("ctc/1.0");
		browserList.add("ctc/2.0");
		browserList.add("cmcc");
		browserList.add("daxian");
		browserList.add("mot-");
		browserList.add("sonyericsson");
		browserList.add("gionee");
		browserList.add("htc");
		browserList.add("zte");
		browserList.add("huawei");
		browserList.add("webos");
		browserList.add("gobrowser");
		browserList.add("iemobile");
		browserList.add("wap2.0");
		browserList.add("ucbrowser");
		browserList.add("ipod");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String requestUri = ((HttpServletRequest)request).getRequestURI();
		
		//获取请求界面的路径
        String temUrl = requestUri.toLowerCase();
		if(temUrl.contains(".css") || temUrl.contains(".js") || temUrl.contains(".png")|| temUrl.contains(".jpg")|| temUrl.contains(".gif")|| temUrl.contains(".ico")){
            //如果发现是css或者js文件，直接放行
            chain.doFilter(request, response);
            return;
        }
		
		// 请求头信息
		String userAgent = ((HttpServletRequest)request).getHeader("user-agent");
		String terminalIP = getIpAddress(((HttpServletRequest)request));
		boolean isMobile = isMobileDevice(userAgent);
		// 设置userAgent
		request.setAttribute("userAgent", userAgent);
		// 设置终端ip
		request.setAttribute("terminalIP", terminalIP);
		// 设置请求终端类型
		if (isMobile) {
			request.setAttribute("terminalType", 2);
		} else {
			request.setAttribute("terminalType", 1);
		}
		
		//cookies处理
		handleCookies(request);
		//请求信息打印
		logger.info("IP：" + terminalIP + "\t"+ (isMobile ? "MO" : "PC")+"\trequestUri:" + requestUri
				 + "\n参数:"+ getParamsInfo(((HttpServletRequest)request)));
		//logger.info("Cookies:"+getCookiesInfo((HttpServletRequest)request));
		
		//对请求进行拦截,防xss处理
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}
	
	/**
	 * 处理cookies
	 * @param request
	 */
	public void handleCookies(ServletRequest request){
		// 读取cookie中存储的uid
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		int uid = 0;
		if (cookies != null && cookies.length > 0) {
			for (Cookie co : cookies) {
				if ((userCookieKey).equals(co.getName())) {
					System.out.println();
					String temp = co.getValue();
					if (temp != null && temp.length() > 0) {
						String[] arr =  Authcode3.authcodeDecode(temp, cookieValSecret).split(",");
						if (arr != null && arr.length >= 1) {
							uid = Integer.parseInt(arr[0]);
						}
					}
				}
				co.setMaxAge(30 * 60);// 过期时间为半小时
			}
		}
		request.setAttribute("uid", uid);
	}

	/**********************************************************************************************************/
	/**
	 * 请求参数转换
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParamsMap(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();
		// 参数处理
		Map<String, String[]> map = request.getParameterMap();
		Set<String> key = map.keySet();
		for (Object obj : key.toArray()) {
			retMap.put(obj.toString(), ((String[]) map.get(obj))[0]);
		}

		return retMap;
	}
	
	/**
	 * 返回请求参数列表
	 * 
	 * @param request
	 * @return
	 */
	public static String getParamsInfo(HttpServletRequest request) {
		return JSONObject.fromObject(getParamsMap(request)).toString();
	}
	
	public static String getCookiesInfo(HttpServletRequest request){
		Map<String, String> retMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie co : cookies) {
				retMap.put(co.getName(), co.getValue());
			}
		}
		return JSONObject.fromObject(retMap).toString();
	}

	/**
	 * 判断请求终端类型
	 * 
	 * @param requestHeader
	 * @return
	 */
	public static boolean isMobileDevice(String userAgent) {
		userAgent = userAgent.toLowerCase();//USER-AGENT字段全部转换成小写
		if (userAgent != null) {
			for(int i=0;i<browserList.size();i++){
				if(userAgent.indexOf(browserList.get(i))>=0){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip != null && ip.indexOf(",") > -1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	
	/*******************************************************************************************/
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		CommonFilter.cookieKeySecret = filterConfig.getInitParameter("cookieKeySecret");
	//	CommonFilter.userCookieKey = Base64.getEncoder().encodeToString((CommonFilter.cookieKeySecret + "user_id_https").getBytes());
		CommonFilter.cookieValSecret = filterConfig.getInitParameter("cookieValSecret");
	}

	@Override
	public void destroy() {
	}
}
