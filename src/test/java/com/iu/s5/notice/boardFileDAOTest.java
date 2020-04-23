package com.iu.s5.notice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.member.memberFile.MemberFileVO;

public class boardFileDAOTest {
	
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	@Test
	public void fileInsertTest() throws Exception{
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setFileNum(1);
		boardFileVO.setNum(1);
		boardFileVO.setFileName("fileName");
		boardFileVO.setOriName("oriName");
		boardFileVO.setBoard(1);
		int result = boardFileDAO.fileInsert(boardFileVO);
		
		assertEquals(1, result);
	}

}
