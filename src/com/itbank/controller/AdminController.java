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
		System.out.println("�׽�Ʈ ����");
		//student.getUp();
		//System.out.println("���� �� �����̳� ���� "+member.getName());
		//System.out.println("�ٸ� �� �����̳� ���� "+dog.getName());
		return null;
	}

	// ������ �α��� ��û
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(Admin admin, HttpServletRequest request) {
		Admin obj = adminService.loginCheck(admin);

		// ���ǿ� ���
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}

	// ���������� ��û
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, String msg) {
		return "admin/index";
	}

	// ȸ������ ������ ��û
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public String member() {
		return "admin/member/index";
	}
	
	// ȸ�� ��� ��û (���� REST API ���°� �ƴ�)
	@RequestMapping(value="/admin/member/regist", method=RequestMethod.POST)
	@ResponseBody  //json�� �ؼ����� ���� responseBody �� json�� �� ������ �ѷ���
							 //���������� Ŭ���̾�Ʈ�� �ƴϴ� (����Ʈ�� �� ���� �ܸ���)
	public String registMember(Member member) {
		System.out.println(member.getId());
		System.out.println(member.getPass());
		System.out.println(member.getName());
		
		memberService.insert(member);
		
		//json �����ϱ�
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
	
	@RequestMapping(value="/admin/member/list", method=RequestMethod.GET)
	@ResponseBody
	public String getList(HttpServletRequest request) {
		System.out.println("ȸ�� ����� ��û�߱���");
		List<Member> memberList=memberService.selectAll();
		
		StringBuilder sb=new StringBuilder();
		
		//��ü�� ���ڿ� ó���ϸ� �����ϴ�, ���� ���� ��ü�� �������
		//�� ��Ʈ�� ó���� json�� ǥ������ ����, ���� json ���� ó���غ���
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
		//������ ǥ���ߴ� json �� ���� ���̺귯���� ǥ���غ���
		//������? ���ڿ� ������� �ʾƵ� �ȴ�
		JSONObject json=new JSONObject();
		
		//���̽� �迭 ����
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
	@ResponseBody  //���䵥���͸� body�� ž����Ѽ� ����
	public String getDetail(@RequestParam("member_id") int member_id) {  //�Ķ���Ͱ� ������ �� 
		System.out.println("�Ѱܹ��� id "+member_id);
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

	// ������ ��û
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
