package com.iu.s5.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iu.s5.member.MemberDAO;
import com.iu.s5.member.MemberVO;
import com.iu.s5.util.Pager;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.iu.s5.member.MemberDAO";
	
	public List<MemberVO> memberList(Pager pager) throws Exception{
		return sqlSession.selectList(NAMESPACE+".memberList", pager);
	}
	
	public long memberCount(Pager pager) throws Exception{
		return sqlSession.selectOne(NAMESPACE+".memberCount", pager);
	}
	
	public int memberDelete(MemberVO memberVO) throws Exception{
		return sqlSession.delete(NAMESPACE+".memberDelete", memberVO);
	}
	
	public int memberUpdate(MemberVO memberVO) throws Exception{
		return sqlSession.update(NAMESPACE+".memberUpdate", memberVO);
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+".memberLogin", memberVO);
	}
	
	
	public int memberJoin(MemberVO memberVO) throws Exception{
		return sqlSession.insert(NAMESPACE+".memberJoin", memberVO);
	}
	
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+".memberIdCheck", memberVO);
	}

	public int memberDeletes(List<String> list) throws Exception{
		return sqlSession.delete(NAMESPACE+".memberDeletes", list);
	}
	




}
