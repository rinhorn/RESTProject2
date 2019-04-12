package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.DeleteFailException;
import com.itbank.common.exception.EditFailException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Member;
import com.itbank.model.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	@Qualifier("mybatisMemberDAO")
	private MemberDAO memberDAO;
	
	public List selectAll() {
		return memberDAO.selectAll();
	}

	public Member select(int member_id) {
		return memberDAO.select(member_id);
	}

	public Member loginCheck(Member member) {
		return null;
	}

	public void insert(Member member) throws RegistFailException{
		int result=memberDAO.insert(member);
		//result=0;  //에러 발생
		if(result==0) {
			throw new RegistFailException("등록되지 않았습니다");
		}
	}

	public void update(Member member) throws EditFailException{
		int result=memberDAO.update(member);
		if(result==0) {
			throw new EditFailException("수정실패");
		}
	}

	public void delete(int member_id) throws DeleteFailException{
		int result=memberDAO.delete(member_id);
		if(result==0) {
			throw new DeleteFailException("삭제실패");
		}
	}
}
