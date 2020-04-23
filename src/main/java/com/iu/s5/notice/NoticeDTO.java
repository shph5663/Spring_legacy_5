package com.iu.s5.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.file.BoardFileVO;

public class NoticeDTO extends BoardDTO {//상속받기
	
	//private BoardDTO boardDTO;

	private List<BoardFileVO> boardFileVOs;

	public List<BoardFileVO> getBoardFileVOs() {
		return boardFileVOs;
	}

	public void setBoardFileVOs(List<BoardFileVO> boardFileVOs) {
		this.boardFileVOs = boardFileVOs;
	}
	
	

}
