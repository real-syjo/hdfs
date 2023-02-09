package com.src.hdfs.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.src.hdfs.model.Board;
import com.src.hdfs.repository.BoardRepository;
import com.src.hdfs.repository.FileRepository;

@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	//저장소 리스트 페이지
	@GetMapping("list")
	public String goList(Model model, Principal principal) {
		
		String username = principal.getName();
		List<Board> boardList = boardRepository.findByUsername(username);
		model.addAttribute("name", username);
		model.addAttribute("boardList", boardList);
		
		return "list";
	}
	
	//게시물 정보 저장 
	@PostMapping(value="/saveBoardInfo")
	@ResponseBody
	public void saveBoardInfo (@RequestBody Board board, Principal principal) {
			Integer totalSize = boardRepository.findAll().size() + 1;
			String code = "BRD";
			String brdId = code + totalSize;
			board.setBoardid(brdId);
			board.setBoardnm("FILE_SET" + totalSize);
			board.setUsername(principal.getName());
			
			boardRepository.save(board);
	}

	//게시물 및 파일 삭제 
	@GetMapping(value="/delFileList")
	public void delFiles(@RequestParam Board board) {
		String id = board.getBoardid();
		boardRepository.deleteByBoardid(id);
		fileRepository.deleteByBoardid(id);
	} 
}
