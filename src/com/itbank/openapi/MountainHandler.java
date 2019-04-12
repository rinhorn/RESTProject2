package com.itbank.openapi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.itbank.model.domain.Mountain;

//������ xml�� �м��Ͽ� �ڹ��� ��ü�� ��ȯ�ϴ� �ڵ鷯 Ŭ����
public class MountainHandler extends DefaultHandler{
	private boolean item;
	private boolean mntiadd;
	private boolean mntidetails;
	
	private List<Mountain> mtList;
	Mountain mt;
	
	public List<Mountain> getMtList() {
		return mtList;
	}

	public void startDocument() throws SAXException {
		mtList=new ArrayList();
	}
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("item")) {
			mt=new Mountain();
			item=true;
		}else if(tag.equals("mntiadd")) {
			mntiadd=true;
		}else if(tag.equals("mntidetails")) {
			mntidetails=true;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String content=new String(ch,start,length);
		
		if(mntiadd) {//���� ��ġ�� �ּҶ��...
			mt.setAddr(content);
		}else if(mntidetails) {//���� ��ġ�� �󼼺�����...
			mt.setDetail(content);
		}
	}
	
	public void endElement(String uri, String localName, String tag) throws SAXException {
		if(tag.equals("item")) {
			item=false;
			mtList.add(mt);
		}else if(tag.equals("mntiadd")) {
			mntiadd=false;
		}else if(tag.equals("mntidetails")) {
			mntidetails=false;
		}
	}

	public void endDocument() throws SAXException {
		System.out.println("������� "+mtList.size());
	}
}
