package com.leting.util.oss.service;

import java.io.File;
import java.io.InputStream;

public interface IOssFileService {
	/**
	 * 向阿里云osss文件服务器上传文件
	 * @param type
	 * @param fileName
	 * @param file
	 * @return
	 */
	public boolean uploadOssFile(String oosFilePath,File file);  
	
	
	/**
	 * 向阿里云osss文件服务器上传文件
	 * @param ossFilePath
	 * @param input
	 * @return
	 */
	public boolean uploadOssFile(String ossFilePath,long fileLenth,InputStream input,String contentType);
	
}
