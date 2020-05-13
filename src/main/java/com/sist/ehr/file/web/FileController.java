package com.sist.ehr.file.web;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sist.ehr.HomeController;
import com.sist.ehr.cmn.StringUtil;
import com.sist.ehr.file.service.FileVO;

@Controller
public class FileController {

	
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    
    private final String UPLOAD_FILE ="D:\\HR_FILE";
	
    @Resource(name = "downloadView")
    private View download;
    
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "file/file_view.do", method = RequestMethod.GET)
	public String home(HttpServletRequest req,Locale locale, Model model ) {
		LOG.info("Welcome home! The client locale is {}.", locale);		
		
		return "file/file";
	}
	
	//download.jsp(X) -> 
	//DownloadView.java extends AbstractView
	//   DispatcherServlet : 
	@RequestMapping(value = "file/download.do", method = RequestMethod.POST)
	public ModelAndView download(HttpServletRequest req,ModelAndView model) {
		//--------------------------------
		//file.jsp -> file/download.do
		//               -download  -> View(downloadView)
		//                          -> DownloadView.java
		//                               renderMergedOutputModel()
		//                               setDownloadFileName()
		//                               downloadFile()
		//--------------------------------
		LOG.debug("========================");
		LOG.debug("=download=");
		LOG.debug("========================");	
		String orgFileNm = req.getParameter("orgFileNm");
		String saveFileNm = req.getParameter("saveFileNm");
		LOG.debug("========================");
		LOG.debug("=orgFileNm="+orgFileNm);
		LOG.debug("=saveFileNm="+saveFileNm);
		LOG.debug("========================");			
		model.setView(this.download);
		
		File   downloadFile =new File(saveFileNm);
		model.addObject("downloadFile", downloadFile);
		model.addObject("orgFileNm", orgFileNm);
		
		
		return model;
	}   
	
	
	@RequestMapping(value = "file/do_insert.do", method = RequestMethod.POST)
	public ModelAndView doInsert(MultipartHttpServletRequest mReg, ModelAndView model) throws IllegalStateException, IOException {
		
		LOG.debug("========================");
		LOG.debug("=doInsert=");
		LOG.debug("========================");
		List<FileVO>  list =new ArrayList<FileVO>();
		//file저장 dir 
		// dir생성 규칙 : D:\\HR_FILE\\ 2020\04 05
		// file: 저장파일명 (PK): YYYYMMDDHH24MISS_UUID
		
		//MultipartHttpServletRequest 파일 정보 read : FileVO-> List
		
		//RootDir생성
		File fileRootDir =new File(this.UPLOAD_FILE);
		if(fileRootDir.isDirectory() ==false) {
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=flag="+flag);
		}
		
		//yyyy
		String yyyyStr = StringUtil.getDate("yyyy");
		LOG.debug("=yyyyStr="+yyyyStr);
		
		//mm
		String mmStr  =  StringUtil.getDate("MM");
		LOG.debug("=mmStr="+mmStr);
		
		//D:\\HR_FILE\\2020\\04
		String datePath = this.UPLOAD_FILE+File.separator+yyyyStr+File.separator+mmStr;
		File fileyearMM = new File(datePath);
		if(fileyearMM.isDirectory() == false) {
			boolean flag = fileyearMM.mkdirs();
			LOG.debug("=flag="+flag);
		}
		  
		//fileRead
		Iterator<String> files =mReg.getFileNames();
		while(files.hasNext()) {
			FileVO  fileVO=new FileVO();
			String upFileNm = files.next();
			LOG.debug("=upFileNm="+upFileNm);
			
			MultipartFile mFile = mReg.getFile(upFileNm);
			String orgFileName = mFile.getOriginalFilename();
			//File 입력이 않된 경우 
			if(null == orgFileName || "".equals(orgFileName) )continue;
			
			LOG.debug("=^^^^^^=");
			fileVO.setOrgFileNm(orgFileName);//원본파일명
			fileVO.setFileSize(mFile.getSize());//파일사이즈
			
			//저장파일명: yyyyMMddHHmmss
			String saveFileName = StringUtil.getDate("yyyyMMddHHmmss")+"_"+StringUtil.getUUID();
			LOG.debug("saveFileName="+saveFileName);
			
			String ext = "";
			//확장자:.
			if(orgFileName.indexOf(".")>-1) {
				ext = orgFileName.substring(orgFileName.indexOf(".")+1);
			}
			saveFileName+="."+ext;
			//Rename
			File renameFile=new File(datePath,saveFileName);
			
			
			fileVO.setSaveFileNm(renameFile.getAbsolutePath());
			fileVO.setExt(ext);
			
			list.add(fileVO);
			mFile.transferTo(new File(renameFile.getAbsolutePath()));
		}
		
			
		
		model.addObject("list", list);
		model.setViewName("file/file");// /file/file.jsp
		return model;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
