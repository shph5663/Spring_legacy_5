package com.iu.s5.board;

import java.util.List;

import com.iu.s5.board.page.Pager;

public interface BoardService {
	
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

}
