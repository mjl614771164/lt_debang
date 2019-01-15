package com.leting.util.oss.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.leting.util.oss.OSSConstants;
import com.leting.util.oss.service.IOssFileService;

@Service("ossFileService")
public class OssFileServiceImpl implements IOssFileService {
	OSSClient client = new OSSClient(OSSConstants.OSS_ENDPOINT,OSSConstants.ACCESS_ID, OSSConstants.ACCESS_KEY);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public boolean uploadOssFile(final String oosFilePath, final File file) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ObjectMetadata objectMeta = new ObjectMetadata();
				objectMeta.setContentLength(file.length());

				InputStream input;
				try {
					input = new FileInputStream(file);
					client.putObject(OSSConstants.BUCKET_NAME, oosFilePath, input,objectMeta);
					System.out.println("本地文件:"+file.getAbsolutePath()+"上传oss成功，oss地址："+oosFilePath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).start();;
		
		return true;
	}
	
	
	public boolean uploadOssFile(String ossFilePath,long fileLenth, InputStream input,String contentType) {
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(fileLenth);
		if(contentType!=null&&!"".equals(contentType)){
			objectMeta.setContentType(contentType);
		}
		try {
			client.putObject(OSSConstants.BUCKET_NAME, ossFilePath, input,objectMeta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
