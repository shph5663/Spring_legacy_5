package com.iu.s5.board.file;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE="com.iu.s5.board.file.BoardFileDAO";
	
	public BoardFileVO fileSelect(BoardFileVO boardFileVO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+".fileSelect", boardFileVO);
	}
	
	//file insert
	
	public int fileInsert(BoardFileVO boardFileVO) throws Exception{
		return sqlSession.insert(NAMESPACE+".fileInsert", boardFileVO);
	}
	
	
	public int fileDelete(long fileNum) throws Exception{
		return sqlSession.delete(NAMESPACE+".fileDelete", fileNum);
	}

}
