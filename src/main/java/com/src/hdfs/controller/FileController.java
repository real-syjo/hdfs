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

import com.src.hdfs.model.Board;
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
	public String saveFileInfo (@RequestBody File files, Principal principal) {
		String name = principal.getName();
		String fileNm = files.getFilenm();
		int idx = name.indexOf("@");
		String dir = name.substring(0, idx);
		
		Integer totalSize = boardRepository.findByUsername(name).size()+1;
		String code = "BRD";
		String brdId = code + "_" + dir + "_" + totalSize;
		File fileChk = fileRepository.findByUsernameAndOrignm(name, fileNm);
		if(fileChk != null) {
//			int lastIdx= (int)boardRepository.count();
//			List<Board> brd = boardRepository.findAll();
//			System.out.println(brd.get(lastIdx).getId());
//			boardRepository.deleteById(brd.get(lastIdx).getId());
			return fileChk.getFilenm();
		}else {
			files.setOrignm(fileNm);
			files.setBoardid(brdId);
			files.setUsername(name);
			files.setHdfsdir("/" + dir);
			System.out.println(files);
			fileRepository.save(files);
			return "";
		}		
	}


	//게시물별 파일 리스트 불러오기
	@PostMapping(value="/getFileList")
	@ResponseBody
	public List<File> getFileList(@RequestBody File file) throws IOException {
		String boardId = file.getBoardid();
		List<File> fileList =  fileRepository.findByBoardid(boardId);
		return fileList;
	}


}
