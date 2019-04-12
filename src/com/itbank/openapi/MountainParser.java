package com.itbank.openapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//xml �� �ڹٰ� �ƴ϶� �׳� �ؽ�Ʈ ����� �������ϻ��̹Ƿ�, json ó�� �Ľ����ؼ� �ڹ�ȭ ���Ѻ���
//xml �Ľ��� json �Ľ̺��ٴ� �ణ ������
//XML �Ľ� ����� ũ�� 2������ �ִ�
//(1) xml �� html �� ���� �±׷� ǥ���Ǵ� ��ũ�� �������̹Ƿ�, DOMȭ ��ų �� �ִ�
//		���� �̷��� DOM ������ �̿��� �Ľ̹���� �ִ�
//		���α׷����� ������ javascript DOM �� ����ϹǷ� ��������, �޸𸮿� �δ��� �ǹǷ�, Ư�� ����Ʈ�� ���߽ÿ� �����Ѵ�
//(2) SAX �Ľ� ��� : �±׸� ���� ������ �Ʒ����� ������� �о���� ���
//							�޸� ȿ������ ������ �ڵ尡 ��ƴ�
//		SAX �ļ��� ���������� �Ʒ��� �о���� �����߿� �±� ���� ������ �̺�Ʈ�� �߻��ϰ�,
//		�����ڴ� �� �̺�Ʈ�� �� �����ؼ� ���ϴ� �±� ������ �ڹٿ� ������Ѿ� �Ѵ�
//		�̶� �� �̺�Ʈ �޼��带 �����ϴ� ��ü�� �ٷ� DefaultHandler �̴�
public class MountainParser {
	SAXParserFactory saxParserFactory;  //�ļ��� �����ϴ� ����
	SAXParser saxParser;  //�Ľ��� ����ϴ� ��ü
	URL url;
	URLConnection con;
	MountainHandler handler;
	
	public MountainParser() {
		saxParserFactory=saxParserFactory.newInstance();
	}
	
	public List getList(String apiKey, String searchWrd){
		List mtList=null;
		try {
			url=new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey="+apiKey+"&searchWrd="+URLEncoder.encode(searchWrd, "UTF-8"));
			con=url.openConnection();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		//�ļ� ����
		try {
			saxParser=saxParserFactory.newSAXParser();
			
			//���ϴ� ��� xml �Ľ� ����
			BufferedReader buffr=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			saxParser.parse(con.getInputStream(), handler=new MountainHandler());

			//�Ľ��� ����Ǿ����Ƿ� ����Ʈ ��������
			mtList=handler.getMtList();
			System.out.println("�Ľ� �Ϸ� �� �Ǽ��� "+mtList.size());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mtList;
	}
	/*
	public static void main(String[] args) {
		new MountainParser();
	}
	*/
}
