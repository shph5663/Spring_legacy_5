package com.iu.s5.qna;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.BoardService;
import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.notice.NoticeDAO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	
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
	public int boardWrite(BoardDTO boardDTO, MultipartFile[] files) throws Exception {
		String path = servletContext.getRealPath("/resource/uploadQna");
		//1. 시퀀스번호받기
		boardDTO.setNum(qnaDAO.boardNum());
		//2. qna 테이블 insert
		int result = qnaDAO.boardWrite(boardDTO);
		//3. HDD에 파일저장하고 boardfile table insert
		for (MultipartFile file : files) {
			BoardFileVO boardFileVO = new BoardFileVO();
			
			String fileName = fileSaver.saveByTransfer(file, path);
			
			boardFileVO.setNum(boardDTO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(2);
			boardFileDAO.fileInsert(boardFileVO);
		}
		
		return result;//qnaDAO.boardWrite(boardDTO);
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
