package com.iu.s5.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.member.memberPage.Pager;

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
	
	
	@RequestMapping(value = "memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(ModelAndView mv, MemberVO memberVO) throws Exception{
		int result = memberService.memberJoin(memberVO);
		return mv;
	}
	
	@RequestMapping(value = "memberLogin")
	public void memberLogin() {
	}
	
	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public ModelAndView memberSelect(ModelAndView mv, MemberVO memberVO)throws Exception {
		memberVO = memberService.memberLogin(memberVO);
		return mv;
	}
	
}
