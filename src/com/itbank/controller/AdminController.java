package com.itbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.common.exception.AccountNotFoundException;
import com.itbank.common.exception.DeleteFailException;
import com.itbank.common.exception.EditFailException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Admin;
import com.itbank.model.domain.Member;
import com.itbank.model.service.AdminService;
import com.itbank.model.service.MemberService;
import com.itbank.test.Dog;
//import com.itbank.test.Member;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	//@Autowired 
	//private Student student;
	
	//@Autowired
	//private Member member;
	
	@Autowired
	private Dog dog;
	
	@Autowired
	private MemberService memberService;
		
	@RequestMapping(value = "/admin/test", method = RequestMethod.GET)
	public String test() {
		System.out.println("테스트 성공");
		//student.getUp();
		//System.out.println("나의 웹 컨테이너 영역 "+member.getName());
		//System.out.println("다른 웹 컨테이너 영역 "+dog.getName());
		return null;
	}

	// 관리자 로그인 요청
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(Admin admin, HttpServletRequest request) {
		Admin obj = adminService.loginCheck(admin);

		// 세션에 담기
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}

	// 메인페이지 요청
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, String msg) {
		return "admin/index";
	}

	// 회원정보 페이지 요청
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public String member() {
		return "admin/member/index";
	}
	
	// 회원 등록 요청 (아직 REST API 쓰는거 아님)
	@RequestMapping(value="/admin/member/regist", method=RequestMethod.POST)
	@ResponseBody  //json을 해석하지 말고 responseBody 에 json을 날 것으로 뿌려라
							 //브라우저만이 클라이언트는 아니다 (스마트폰 등 각종 단말기)
	public String registMember(Member member) {
		System.out.println(member.getId());
		System.out.println(member.getPass());
		System.out.println(member.getName());
		
		memberService.insert(member);
		
		//json 구성하기
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
	
	@RequestMapping(value="/admin/member/list", method=RequestMethod.GET)
	@ResponseBody
	public String getList(HttpServletRequest request) {
		System.out.println("회원 목록을 요청했군요");
		List<Member> memberList=memberService.selectAll();
		
		StringBuilder sb=new StringBuilder();
		
		//객체를 문자열 처리하면 불편하다, 따라서 실제 객체로 취급하자
		//즉 스트링 처리로 json을 표현하지 말고, 실제 json 으로 처리해보자
		/*
		sb.append("{");
		sb.append("\"memberList\":[");
		for(int i=0;i<memberList.size();i++) {
			Member member=(Member)memberList.get(i);
			sb.append("{");
			sb.append("\"member_id\":"+member.getMember_id()+",");
			sb.append("\"id\":\""+member.getId()+"\",");
			sb.append("\"pass\":\""+member.getPass()+"\",");
			sb.append("\"name\":\""+member.getName()+"\"");
			if(i<memberList.size()-1) {
				sb.append("},");
			}else {
				sb.append("}");
			}
		}
		sb.append("]");
		sb.append("}");
		
		request.getSession().setAttribute("memberList", sb.toString());
		System.out.println(sb.toString());
		*/
		//위에서 표현했던 json 을 구글 라이브러리로 표현해보자
		//차이점? 문자열 취급하지 않아도 된다
		JSONObject json=new JSONObject();
		
		//제이슨 배열 생성
		JSONArray jsonArray=new JSONArray();
		
		for(int i=0;i<memberList.size();i++) {
			Member member=memberList.get(i);
			JSONObject obj=new JSONObject();
			obj.put("member_id", member.getMember_id());
			obj.put("id", member.getId());
			obj.put("pass", member.getPass());
			obj.put("name", member.getName());
			
			jsonArray.add(obj);
		}
		
		json.put("memberList", jsonArray);
		
		return json.toString();
	}
	
	@RequestMapping(value="/admin/member/detail", method=RequestMethod.GET)
	@ResponseBody  //응답데이터를 body에 탑재시켜서 응답
	public String getDetail(@RequestParam("member_id") int member_id) {  //파라미터가 단일일 때 
		System.out.println("넘겨받은 id "+member_id);
		Member member=memberService.select(member_id);
		
		JSONObject json=new JSONObject();
		json.put("member_id", member.getMember_id());
		json.put("id", member.getId());
		json.put("pass", member.getPass());
		json.put("name", member.getName());
		return json.toString();
	}
	
	@RequestMapping(value="/admin/member/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("member_id") int member_id) {
		memberService.delete(member_id);
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
	
	@RequestMapping(value="/admin/member/edit", method=RequestMethod.POST)
	@ResponseBody
	public String update(Member member) {
		memberService.update(member);
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}

	// 맵정보 요청
	@RequestMapping(value = "/admin/map", method = RequestMethod.GET)
	public String map() {
		return "admin/map/index";
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ModelAndView handleException(AccountNotFoundException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/error/errorpage");
		mav.addObject("err", e);
		return mav;
	}
	
	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public String registException(RegistFailException e) {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("}");
		return sb.toString();
	}
	
	@ExceptionHandler(DeleteFailException.class)
	@ResponseBody
	public String deleteException(DeleteFailException e) {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("}");
		return sb.toString();
	}
	
	@ExceptionHandler(EditFailException.class)
	@ResponseBody
	public String updateException(EditFailException e) {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("}");
		return sb.toString();
	}
}
