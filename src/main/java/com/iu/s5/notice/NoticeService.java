package com.iu.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.BoardService;
import com.iu.s5.board.page.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<BoardDTO> boardList(Pager pager) throws Exception {
		
		pager.makeRow();
		long totalCount = noticeDAO.boardCount(pager);
		pager.makePage(totalCount);
		
		return noticeDAO.boardList(pager);
	}

	@Override
	public BoardDTO boardSelect(long num) throws Exception {
		noticeDAO.hitUpdate(num);
		return noticeDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		
		return noticeDAO.boardWrite(boardDTO);
	}

	@Override
	public int boardDelete(long num) throws Exception {
		
		return noticeDAO.boardDelete(num);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {

		return noticeDAO.boardUpdate(boardDTO);
	}

}
