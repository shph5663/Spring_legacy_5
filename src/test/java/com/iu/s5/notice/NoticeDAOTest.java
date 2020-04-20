package com.iu.s5.notice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;
import com.iu.s5.board.BoardDTO;

public class NoticeDAOTest extends AbstractTestCase {

	@Autowired
	private NoticeDAO noticeDAO;

	@Test
	public void daoTest()throws Exception{
		//List<BoardDTO> ar = this.boardListTest();
		//assertNotEquals(0, ar.size());\
		this.boardWriteTest();
	}
	
	
	public void daoIsnull() {
		assertNotNull(noticeDAO);
	}
	

	public void boardWriteTest() throws Exception{
		String writer="";
		String title="";
		String contents="";
		
		for (int i = 0; i < 150; i++) {
		NoticeDTO noticeDTO = new NoticeDTO();
		if (i%3==0) {
			writer="iu";
			title="alert";
			contents="samsung";
		}else if (i%3==1) {
			writer="choa";
			title="computer";
			contents="apple";
		}else {
			writer="suji";
			title="os";
			contents="linux";
		}
		
		noticeDTO.setTitle(title+i);
		noticeDTO.setWriter(writer);
		noticeDTO.setContents(contents+i);
		int result = noticeDAO.boardWrite(noticeDTO);
		if(i==50||i==100) {
			Thread.sleep(1000);
		}
		}
		//assertEquals(1, result);
	}

	public void boardDeleteTest() throws Exception{
		
		int result = noticeDAO.boardDelete(6);
		
		assertNotEquals(0, result);
	}
	

	public void boardUpdateTest() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle("HI");
		noticeDTO.setWriter("IU");
		noticeDTO.setContents("Hello");
		noticeDTO.setNum(7);
		int result = noticeDAO.boardUpdate(noticeDTO);
		
		assertEquals(1, result);
		
	}
	
	
	public void boardSelectTest() throws Exception{
		BoardDTO boardDTO = noticeDAO.boardSelect(7);
		assertNotNull(boardDTO);
	}
	

//	public List<BoardDTO> boardListTest() throws Exception{
//		return noticeDAO.boardList();
//	}
	

}
