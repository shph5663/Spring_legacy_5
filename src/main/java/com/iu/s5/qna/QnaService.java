package com.iu.s5.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.BoardService;
import com.iu.s5.notice.NoticeDAO;
import com.iu.s5.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	public int boardReply(BoardDTO boardDTO) throws Exception{
		int result = qnaDAO.boardReplyUpdate(boardDTO);
		result = qnaDAO.boardReply(boardDTO);
		return result;
	}

	@Override
	public List<BoardDTO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePage(qnaDAO.boardCount(pager));
		return qnaDAO.boardList(pager);
	}

	@Override
	public BoardDTO boardSelect(long num) throws Exception {
		
		return qnaDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return qnaDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardDelete(long num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
