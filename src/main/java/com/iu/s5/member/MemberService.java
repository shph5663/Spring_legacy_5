package com.iu.s5.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.member.memberFile.MemberFileDAO;
import com.iu.s5.member.memberFile.MemberFileVO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private MemberFileDAO memberFileDAO;
	
	public List<MemberVO> memberList(Pager pager) throws Exception{
		pager.makeRow();
		System.out.println(pager.getKind());
		System.out.println(pager.getSearch());
		long totalCount = memberDAO.memberCount(pager);
		System.out.println("asdasd");
		pager.makePage(totalCount);
	
		return memberDAO.memberList(pager);

	}

	public int memberUpdate(MemberVO memberVO)throws Exception{
		return memberDAO.memberUpdate(memberVO);
	}
	
	public int memberDelete(MemberVO memberVO) throws Exception{
		return memberDAO.memberDelete(memberVO);
	}
	
	public MemberVO memberLogin(MemberVO memberVO)throws Exception{
		return memberDAO.memberLogin(memberVO);
	}
	
	public int memberJoin(MemberVO memberVO, MultipartFile avatar, HttpSession session)throws Exception{
		//HDD에 저장 resources/memberUpload/
		//1. 실제 경로
		String path = session.getServletContext().getRealPath("/resources/memberUpload");
		System.out.println(path);
		String fileName = fileSaver.saveByTransfer(avatar, path);
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setId(memberVO.getId());
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(avatar.getOriginalFilename());
		//2. 파일명을 DB에 저장
		
		 int result = memberDAO.memberJoin(memberVO);
		 result = memberFileDAO.fileInsert(memberFileVO);
		return result;//memberDAO.memberJoin(memberVO);
	}
	
//	public MemberFileVO fileSelect(String id)throws Exception{
//		return memberFileDAO.fileSelect(id);		
//	}
	
	public int fileDelete(String id, HttpSession session) throws Exception{
		MemberFileVO memberFileVO = memberFileDAO.fileSelect(id);
		
		int result = memberFileDAO.fileDelete(id);
		if (result>0) {
			String path = session.getServletContext().getRealPath("/resources/memberUpload");
			result = fileSaver.deleteFile(memberFileVO.getFileName(), path);
		}
		return result;		
	}
	
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception{
		return memberDAO.memberIdCheck(memberVO);
	}
	
	public int memberDeletes(List<String> list) throws Exception{
		return memberDAO.memberDeletes(list);
	}
	
}
	
