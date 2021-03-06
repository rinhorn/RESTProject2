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

//xml 은 자바가 아니라 그냥 텍스트 기반의 데이터일뿐이므로, json 처럼 파싱을해서 자바화 시켜보자
//xml 파싱은 json 파싱보다는 약간 복잡함
//XML 파싱 방법은 크게 2가지가 있다
//(1) xml 도 html 과 같은 태그로 표현되는 마크업 랭기지이므로, DOM화 시킬 수 있다
//		따라서 이러한 DOM 원리를 이용한 파싱방법이 있다
//		프로그래밍은 기존의 javascript DOM 과 비슷하므로 편하지만, 메모리에 부담이 되므로, 특히 스마트폰 개발시엔 지양한다
//(2) SAX 파싱 방식 : 태그를 문서 위부터 아래까지 순서대로 읽어나가는 방식
//							메모리 효율성이 높지만 코드가 어렵다
//		SAX 파서는 위에서부터 아래로 읽어나가는 과정중에 태그 등을 만나면 이벤트가 발생하고,
//		개발자는 이 이벤트를 잘 제어해서 원하는 태그 정보를 자바와 연계시켜야 한다
//		이때 이 이벤트 메서드를 지원하는 객체가 바로 DefaultHandler 이다
public class MountainParser {
	SAXParserFactory saxParserFactory;  //파서를 생성하는 공장
	SAXParser saxParser;  //파싱을 담당하는 객체
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
	
		//파서 생성
		try {
			saxParser=saxParserFactory.newSAXParser();
			
			//원하는 대상 xml 파싱 시작
			BufferedReader buffr=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			saxParser.parse(con.getInputStream(), handler=new MountainHandler());

			//파싱이 종료되었으므로 리스트 가져오자
			mtList=handler.getMtList();
			System.out.println("파싱 완료 후 건수는 "+mtList.size());
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
