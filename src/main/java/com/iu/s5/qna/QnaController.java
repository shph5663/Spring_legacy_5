package com.iu.s5.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard()throws Exception{
		return "qna";
	}
	
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager, ModelAndView mv) throws Exception {
		
		List<BoardDTO> ar = qnaService.boardList(pager);
		mv.setViewName("board/boardList");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		return mv;
	}
	
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite(ModelAndView mv) throws Exception{
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(QnaVO qnaVO, ModelAndView mv) throws Exception{
		int result = qnaService.boardWrite(qnaVO);
		String msg = "qna qrite fail";
		if (result>0) {
			msg = "qna write success";
		}
		
		mv.addObject("result", msg);
		mv.addObject("path", "./qnaList");
		
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(long num, ModelAndView mv) throws Exception{
		BoardDTO boardDTO = qnaService.boardSelect(num);
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv, long num) throws Exception{
		mv.addObject("num", num);
		mv.setViewName("board/boardReply");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv, QnaVO qnaVO)throws Exception{
		int result = qnaService.boardReply(qnaVO);
		if (result>0) {
			mv.setViewName("redirect:./qnaList");
		}else {
			mv.addObject("result", "reply write fail");
			mv.addObject("path", "./qnaList");
			
			mv.setViewName("common/result");
		}
		return mv;
	}
}



















