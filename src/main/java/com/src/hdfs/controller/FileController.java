package com.src.hdfs.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.src.hdfs.model.File;
import com.src.hdfs.repository.BoardRepository;
import com.src.hdfs.repository.FileRepository;

@RestController
public class FileController {
	
	@Autowired
	static SessionFactory factory;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	
	//파일 정보 저장 
	@PostMapping(value="/saveFileInfo")
	@ResponseBody
	public void saveFileInfo (@RequestBody File files, Principal principal) {
		String name = principal.getName();
		Integer brdSize = boardRepository.findAll().size()+1;
		String brdCode = "BRD";
		int idx = name.indexOf("@");
		String dir = name.substring(0,idx);
		
//		//중복체크 
//		List<File> existFile = fileRepository.findByUsername(principal.getName());
//		String uploadFileName= files.getFilenm();
//		
//		files.setBoardid(brdCode+brdSize);
//		files.setUsername(principal.getName());
//		files.setHdfsdir("/"+dir);
//		
//		if(existFile.isEmpty()) {
//		}else {
//			for(int i=0; i<existFile.size(); i++) {
//				String existFileName = existFile.get(i).getFilenm();
//				if( existFileName.equals(uploadFileName)) {
//					int num= i;
//					files.setFilenm(uploadFileName+"("+num+")");
//				}
//			}
//		}
		files.setBoardid(brdCode+brdSize);
		files.setUsername(principal.getName());
		files.setHdfsdir("/"+dir);
		
		fileRepository.save(files);
	}
	
	//게시물별 파일 리스트 불러오기
	@PostMapping(value="/getFileList")
	public List<File> getFileList(@RequestBody File file) throws IOException {

		String boardId = file.getBoardid();
		List<File> fileList =  fileRepository.findByBoardid(boardId);
		
		return fileList;
	}
	
	
	


}
