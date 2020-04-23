package com.iu.s5.member;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.member.memberFile.MemberFileVO;
import com.iu.s5.util.Pager;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value = "memberList", method = RequestMethod.GET)
	public ModelAndView memberList(ModelAndView mv, Pager pager) throws Exception{
		List<MemberVO> ar = memberService.memberList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("member/memberList");
		return mv;
	}
	
	@RequestMapping(value = "memberJoin")
	public void memberJoin() {
	}
	
	
	@RequestMapping(value= "memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(MemberVO memberVO, ModelAndView mv,HttpSession session ,MultipartFile avatar) throws Exception {
		
		
		
		int result = memberService.memberJoin(memberVO, avatar, session);
		String msg ="Member Join Fail";
		if(result>0) {
			msg = "Member Join Success";
		}
		
		mv.addObject("result", msg);
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	@RequestMapping(value = "memberLogin")
	public void memberLogin(@CookieValue(value = "cId", required = false) String cId, Model model) {
		System.out.println(cId);
		model.addAttribute("cId", cId);
	}
	
	@RequestMapping(value= "memberLogin", method = RequestMethod.POST)
	public ModelAndView memberLogin(ModelAndView mv,String remember, MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie("cId", "");
		
		if (remember!=null) {
			//cookie = new Cookie("cId", memberVO.getId());
			cookie.setValue(memberVO.getId());
		}
		response.addCookie(cookie);
		
		//cookie.setMaxAge(30);

		 memberVO = memberService.memberLogin(memberVO);
		
		 if(memberVO != null) {
			 session.setAttribute("member", memberVO);
			 mv.setViewName("redirect:../");
		 }else {
			 mv.addObject("result", "Login Fail");
			 mv.addObject("path", "./memberJoin");
			 mv.setViewName("common/result");
		 }
		 
		//로그인 성공이면 index
		//로그인 실패 하면 로그인 실패 alert login form 이동		 
				 
				 
		return mv;
	}
	
	@RequestMapping(value = "memberLogout")
	public String memberLogout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	@RequestMapping(value= "memberPage")
	public void memberPage(HttpSession session, Model model)throws Exception {
//		MemberVO memberVO = (MemberVO)session.getAttribute("member");
//		MemberFileVO memberFileVO= memberService.fileSelect(memberVO.getId());
//		model.addAttribute("file", memberFileVO);
	}
	
	@RequestMapping(value= "fileDelete")//a태그니까 get
	public String fileDelete(HttpSession session)throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberService.fileDelete(memberVO.getId(), session);
		return "redirect :./memberPage";
	}
	
}
