package com.iu.s5.board;

import java.util.ArrayList;
import java.util.List;

public interface BoardDAO {
	//멤버변수 상수
	//추상메서드
	//public abstract 필수
	//생략해도 자동으로 만들어짐

	//List
	public List<BoardDTO> boardList() throws Exception;//여기까지가 추상메서드
	
	//select
	public BoardDTO boardSelect () throws Exception;
	
	//insert
	public int boardWrite(BoardDTO boardDTO) throws Exception;
	
	//delete
	public int boardDelete(long num) throws Exception;
	
	//update
	public int boardUpdate(BoardDTO boardDTO) throws Exception;
}

