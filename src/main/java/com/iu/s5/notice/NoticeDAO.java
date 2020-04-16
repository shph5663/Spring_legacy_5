package com.iu.s5.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.s5.board.BoardDAO;
import com.iu.s5.board.BoardDTO;

@Repository
public class NoticeDAO implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.iu.s5.notice.NoticeDAO";
	
	@Override
	public List<BoardDTO> boardList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO boardSelect() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+".boardWrite", boardDTO);	
	}
	
	@Override
	public int boardDelete(long num) throws Exception{
		return sqlSession.delete(NAMESPACE+".boardDelete", num);	
	}
	
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception{
		
		return sqlSession.update(NAMESPACE+".boardUpdate", boardDTO);
	}

}
