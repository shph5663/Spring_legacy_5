package com.iu.s5.notice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;

public class NoticeDAOTest extends AbstractTestCase {

	@Autowired
	private NoticeDAO noticeDAO;


//	@Test
//	public void daoIsnull() {
//		assertNotNull(noticeDAO);
//	}
//	
//	@Test
//	public void boardWriteTest() throws Exception{
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setTitle("test title");
//		noticeDTO.setWriter("test writer");
//		noticeDTO.setContents("test contents");
//		int result = noticeDAO.boardWrite(noticeDTO);
//		
//		assertEquals(1, result);
//	}
//	@Test
//	public void boardDeleteTest() throws Exception{
//		
//		int result = noticeDAO.boardDelete(6);
//		
//		assertNotEquals(0, result);
//	}
//	
//	@Test
//	public void boardUpdateTest() throws Exception{
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setTitle("HI");
//		noticeDTO.setWriter("IU");
//		noticeDTO.setContents("Hello");
//		noticeDTO.setNum(7);
//		int result = noticeDAO.boardUpdate(noticeDTO);
//		
//		assertEquals(1, result);
//		
//	}
	
	@Test
	public void boardUpdateTest() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setHit(1);
		noticeDTO.setNum(7);
		int result = noticeDAO.boardUpdate(noticeDTO);
		
		assertEquals(1, result);
		
	}
}
