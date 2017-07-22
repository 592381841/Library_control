package com.li.common.dto;

import java.io.InputStream;

public class DownloadInformation {
	public DownloadInformation() {
		// TODO Auto-generated constructor stub
	}

	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	private String fileName;
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	private String contentType;
	public DownloadInformation(String fileName, String contentType) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
