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
		String path = servletContext.getRealPath("/resources/uploadnotice");
		System.out.println(path);
		//sequence 번호 받기
		boardDTO.setNum(noticeDAO.boardNum());
		
		//notice table insert
		int result = noticeDAO.boardWrite(boardDTO);
		
		for (MultipartFile file : files) {
			if(file.getSize()>1) {
			BoardFileVO boardFileVO = new BoardFileVO();
			String fileName = fileSaver.saveByTransfer(file, path);	
			boardFileVO.setNum(boardDTO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setBoard(1);
			boardFileDAO.fileInsert(boardFileVO);
			}
		}
		return result;//noticeDAO.boardWrite(boardDTO);

	}

	@Override
	public int boardDelete(long num) throws Exception {
		List<BoardFileVO> list = boardFileDAO.fileList(num);
		//1. HDD에 해당 파일 삭제
		String path = servletContext.getRealPath("/resources/uploadnotice");
		System.out.println(path);
		for (BoardFileVO boardFileVO : list) {
			fileSaver.deleteFile(boardFileVO.getFileName(), path);
		}
		//2. DB에삭제
		
		boardFileDAO.fileDeleteAll(num);
		
		return noticeDAO.boardDelete(num);
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO, MultipartFile [] files) throws Exception {
		//HDD에 파일 저장
		String path = servletContext.getRealPath("/resources/uploadnotice");
		System.out.println(path);
		int result = noticeDAO.boardUpdate(boardDTO);

		for (MultipartFile multipartFile : files) {
			if(multipartFile.getSize()>1) {
			BoardFileVO boardFileVO = new BoardFileVO();
			String fileName = fileSaver.saveByUtils(multipartFile, path);//리턴으로 파일 이름이옴
			boardFileVO.setNum(boardDTO.getNum());
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setBoard(1);
			result = boardFileDAO.fileInsert(boardFileVO);
			}
		}
		
		return result;
	}

	@Override
	public int boardDelete(long num, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
