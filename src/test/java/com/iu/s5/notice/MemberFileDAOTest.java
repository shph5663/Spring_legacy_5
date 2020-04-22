package com.iu.s5.notice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;
import com.iu.s5.member.memberFile.MemberFileDAO;
import com.iu.s5.member.memberFile.MemberFileVO;

public class MemberFileDAOTest extends AbstractTestCase{
	
	@Autowired
	private MemberFileDAO memberFileDAO;

//	@Test
//	public void daoTest()throws Exception{
//
//		this.memberFileInsertTest();
//	}
	
	@Test
	public void fileInsertTest() throws Exception{
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId("iu0520");
		memberFileVO.setFileName("iu0520_file");
		memberFileVO.setOriName("iu0520_ori");
		
		int result = memberFileDAO.fileInsert(memberFileVO);
		
		assertEquals(1, result);
	}

}
