package com.leting.util.oss;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 阿里云oss文件存储
 * @author liuyadong
 *
 */
public class OSSConstants {
	private static Logger logger = Logger.getLogger(OSSConstants.class);
	
	public static String ACCESS_ID = null;
	public static String ACCESS_KEY = null;
	public static String OSS_ENDPOINT = null;
	public static String BUCKET_NAME = null;
	public static String CDN_BASEURL_FILEUPLOAD = null;//cdn资源文件url根路径
	public static String CDN_BASEURL_FILEUPLOAD_IMG = null;//cdn图片资源url跟路径
    
    /**
     * 读取配置文件获取配置信息
     */
  	static {
  		Properties pps = new Properties();
  		try {
  			InputStream is = OSSConstants.class.getClassLoader().getResourceAsStream("conf/oss/oss.properties");
  			pps.load(is);
  			
  			ACCESS_ID = pps.getProperty("ACCESS_ID");
  			ACCESS_KEY = pps.getProperty("ACCESS_KEY");
  			OSS_ENDPOINT = pps.getProperty("OSS_ENDPOINT");
  			BUCKET_NAME = pps.getProperty("BUCKET_NAME");
  			CDN_BASEURL_FILEUPLOAD = pps.getProperty("CDN_BASEURL_FILEUPLOAD");
  			CDN_BASEURL_FILEUPLOAD_IMG = pps.getProperty("CDN_BASEURL_FILEUPLOAD_IMG");
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  			logger.error(e.getMessage()+e.getStackTrace());
  		} catch (IOException e) {
  			e.printStackTrace();
  			logger.error(e.getMessage()+e.getStackTrace());
  		}
  	}
}
