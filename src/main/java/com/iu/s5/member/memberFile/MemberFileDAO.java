package com.iu.s5.member.memberFile;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberFileDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE="com.iu.s5.member.memberFile.MemberFileDAO";
	
	//file insert
	
	public int fileInsert(MemberFileVO memberFileVO) throws Exception{
		return sqlSession.insert(NAMESPACE+".fileInsert", memberFileVO);
	}
	
	//file update
	
	public int fileUpdate(MemberFileVO memberFileVO) throws Exception{
		return sqlSession.update(NAMESPACE+".fileUpdate", memberFileVO);
	}
	
	//file delete
	
	public int fileDelete(String id) throws Exception{//memberFileVO 받아도 상관없음
		return sqlSession.delete(NAMESPACE+".fileDelete", id);
	}
	
	//file select
	
	public MemberFileVO fileSelect(String id) throws Exception{
		return sqlSession.selectOne(NAMESPACE+".fileSelect", id);
	}
}
