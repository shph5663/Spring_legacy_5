package com.iu.s5.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.board.page.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard()throws Exception{
		return "notice";
	}
	
	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(long num, ModelAndView mv)throws Exception{
		int result = noticeService.boardDelete(num);
		if(result>0) {
			mv.addObject("result", "Delete Success");
		}else {
			mv.addObject("result", "Delete Fail");
		}
		mv.addObject("path", "./noticeList");
		mv.setViewName("common/result");
		return mv;
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public String boardUpdate(long num, Model model)throws Exception{
		 BoardDTO boardDTO = noticeService.boardSelect(num);
		 model.addAttribute("dto", boardDTO);
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public String boardUpdate(NoticeDTO noticeDTO)throws Exception{
		 
		int result = noticeService.boardUpdate(noticeDTO);
		String path="";
		
		if(result>0) {
			path= "redirect:./noticeList";
		}else {
			path= "redirect:./noticeSelect?num="+noticeDTO.getNum();
		}
		 
		return path;
	}
	
	
	
	@RequestMapping(value = "noticeWrite", method = RequestMethod.GET)
	public String boardWrite()throws Exception{
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(NoticeDTO noticeDTO, ModelAndView mv)throws Exception{
		int result = noticeService.boardWrite(noticeDTO);
		if(result>0) {
			mv.setViewName("redirect:./noticeList");
		}else {
			mv.addObject("result", "Write Fail");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/result");
		}
		return mv;
	}
	
	@RequestMapping(value="noticeSelect" , method = RequestMethod.GET)
	public ModelAndView boardSelect(long num)throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardDTO boardDTO = noticeService.boardSelect(num);
		mv.addObject("dto", boardDTO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(Pager pager, ModelAndView mv)throws Exception{
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
		
		 List<BoardDTO> ar = noticeService.boardList(pager);
		 System.out.println(pager.getTotalPage());
		 mv.addObject("list", ar);
		 mv.addObject("pager", pager);
		 mv.setViewName("board/boardList");
		 return mv;
	}

}