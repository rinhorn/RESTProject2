package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
//javaSE에 이미 보유
import org.xml.sax.helpers.DefaultHandler;

//xml을 파싱할 때 각종 이벤트를 발생시키는 객체
public class MyHandler extends DefaultHandler{
	List<EMP> memberList;  
	EMP emp=null;
	
	//현재 실행부의 위치를 알 수 있는 단서
	private boolean member;
	private boolean id;
	private boolean name;
	private boolean sal;
	
	public void startDocument() throws SAXException {
		System.out.println("문서의 시작입니다");
		memberList=new ArrayList();
		//사원이 없을 경우 사이즈가 0이어야 한다, 즉 null 이면 안된다
	}
	
	public void startElement(String url, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		
		if(tag.equals("member")) {  //DTO 한개를 생성
			emp=new EMP();
			member=true;
		}if(tag.equals("id")) {
			id=true;
		}if(tag.equals("name")) {
			name=true;
		}if(tag.equals("sal")) {
			sal=true;
		}
	}
	
	//태그 사이에 들어가는 컨텐츠를 감지하여 알려주는 메서드
	//즉 태그 사이의 글씨를 만나면 이 메서드가 동작한다
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content=new String(ch, start, length);
		System.out.print(content);
		
		if(id) {
			emp.setId(content);
		}else if(name) {
			emp.setName(content);
		}else if(sal) {
			emp.setSal(Integer.parseInt(content));
		}
	}
	
	public void endElement(String url, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
		
		if(tag.equals("member")) {  //DTO 한개를 생성
			member=false;
			//리스트에 담기
			memberList.add(emp);
		}if(tag.equals("id")) {
			id=false;
		}if(tag.equals("name")) {
			name=false;
		}if(tag.equals("sal")) {
			sal=false;
		}
	}
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝입니다, 최종 개수 "+memberList.size());
	}
}
