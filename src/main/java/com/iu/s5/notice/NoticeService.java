package com.iu.s5.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.BoardService;
import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private BoardFileDAO boardFileDAO;
	
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
	public int boardWrite(BoardDTO boardDTO, MultipartFile [] files) throws Exception {
		System.out.println(files);
		String path = servletContext.getRealPath("/resource/uploadNotice");
		System.out.println(path);
		//sequence 번호 받기
		boardDTO.setNum(noticeDAO.boardNum());
		
		//notice table insert
		int result = noticeDAO.boardWrite(boardDTO);
		
		for (MultipartFile file : files) {
			BoardFileVO boardFileVO = new BoardFileVO();
			String fileName = fileSaver.saveByTransfer(file, path);	
			boardFileVO.setNum(boardDTO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(1);
			boardFileDAO.fileInsert(boardFileVO);
		}
		return result;//noticeDAO.boardWrite(boardDTO);

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
