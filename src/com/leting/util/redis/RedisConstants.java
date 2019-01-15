package com.leting.util.redis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisConstants {
	public static String IP = "";//服务ip地址
	public static int PORT;//服务器端口号
	public static String AUTH;//授权
	public static int TIMEOUT;//超时时间
	
	//初始化上述参数
	static {
		Properties pps = new Properties();
		try {
			InputStream is = RedisConstants.class.getClassLoader().getResourceAsStream("conf/redis/redis.properties");
			pps.load(is);
			
			IP = pps.getProperty("IP");
			PORT = Integer.parseInt(pps.getProperty("PORT"));
			AUTH = pps.getProperty("AUTH");
			TIMEOUT = Integer.parseInt(pps.getProperty("TIMEOUT"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
