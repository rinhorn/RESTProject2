package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
//javaSE�� �̹� ����
import org.xml.sax.helpers.DefaultHandler;

//xml�� �Ľ��� �� ���� �̺�Ʈ�� �߻���Ű�� ��ü
public class MyHandler extends DefaultHandler{
	List<EMP> memberList;  
	EMP emp=null;
	
	//���� ������� ��ġ�� �� �� �ִ� �ܼ�
	private boolean member;
	private boolean id;
	private boolean name;
	private boolean sal;
	
	public void startDocument() throws SAXException {
		System.out.println("������ �����Դϴ�");
		memberList=new ArrayList();
		//����� ���� ��� ����� 0�̾�� �Ѵ�, �� null �̸� �ȵȴ�
	}
	
	public void startElement(String url, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		
		if(tag.equals("member")) {  //DTO �Ѱ��� ����
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
	
	//�±� ���̿� ���� �������� �����Ͽ� �˷��ִ� �޼���
	//�� �±� ������ �۾��� ������ �� �޼��尡 �����Ѵ�
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
		
		if(tag.equals("member")) {  //DTO �Ѱ��� ����
			member=false;
			//����Ʈ�� ���
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
		System.out.println("������ ���Դϴ�, ���� ���� "+memberList.size());
	}
}
