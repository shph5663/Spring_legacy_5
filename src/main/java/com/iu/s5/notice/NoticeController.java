package com.iu.s5.notice;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardDTO;
import com.iu.s5.util.Pager;

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
		 
		 NoticeDTO noticeDTO = (NoticeDTO)boardDTO;
		 model.addAttribute("size", noticeDTO.getBoardFileVOs().size());
		 
		return "board/boardUpdate";
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public String boardUpdate(NoticeDTO noticeDTO, MultipartFile [] files)throws Exception{
		 for (MultipartFile multipartFile:files) {
			System.out.println(multipartFile.getOriginalFilename());
		}
		int result = noticeService.boardUpdate(noticeDTO, files);
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
	public ModelAndView boardWrite(NoticeDTO noticeDTO,MultipartFile [] files , ModelAndView mv)throws Exception{
			//HttpServletRequest request 변수 주고
//		Enumeration<String> er = request.getParameterNames();
//		
//		while (er.hasMoreElements()) {
//			System.out.println(er.nextElement());
//		}
		
		int result = noticeService.boardWrite(noticeDTO, files);
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
