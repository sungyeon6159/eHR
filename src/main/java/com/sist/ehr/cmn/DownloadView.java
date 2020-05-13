package com.sist.ehr.cmn;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView {
	private final Logger  LOG = LoggerFactory.getLogger(DownloadView.class);
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	//파일명 지정
	private void setDownloadFileName(String fileName
			, HttpServletRequest request
			, HttpServletResponse response) {
		
		
		
	}
	
	//파일 다운로드
	private void downloadFile(File downloadFile 
			, HttpServletRequest request
			, HttpServletResponse response) {
		
	}
	
	
	//DownloadView 진입 메소드:1
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			
			setResponseContentType(request, response);
			File downloadFile = (File) model.get("downloadFile");
            String orgFileNm  = (String) model.get("orgFileNm");
			LOG.debug("1-renderMergedOutputModel-");
			LOG.debug("1-orgFileNm-"+orgFileNm);
			LOG.debug("1-downloadFile----------------"+downloadFile.getName());
			LOG.debug("1-length-------------"+downloadFile.length());
			LOG.debug("1--------------------------");
			
			
			
			
			
			
		}catch(Exception e) {
			throw e;
		}

	}

}








