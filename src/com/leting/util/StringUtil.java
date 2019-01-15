package com.leting.util;

public class StringUtil {
	private static String[] data = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	/**
	 * 获取指定长度的数字验证码
	 * @param length
	 * @return
	 */
	public static String getRandomNumCode(int length) {
		if(length<=0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(data[(int) (Math.random() * data.length)]);
		}
		return sb.toString();
	}
}
