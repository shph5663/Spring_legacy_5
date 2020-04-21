package com.iu.s5.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.iu.s5.util.Pager;

public interface BoardDAO {
	//멤버변수 상수
	//추상메서드
	//public abstract 필수
	//생략해도 자동으로 만들어짐
	
	//count
	public long boardCount(Pager pager)throws Exception;
		
	//List
	public List<BoardDTO> boardList(Pager pager) throws Exception;//여기까지가 추상메서드
	
	//select
	public BoardDTO boardSelect(long num) throws Exception;
	
	//insert
	public int boardWrite(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int boardDelete(long num) throws Exception;
	
	//update
	public int boardUpdate(BoardDTO boardDTO) throws Exception;

	//hit update
	public int hitUpdate(long num)throws Exception;
}

