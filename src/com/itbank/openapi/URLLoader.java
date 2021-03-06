package com.itbank.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//로컬 파일이 아닌, 네트워크상에 존재하는 자원을 실행중인 어플리케이션으로 가져오는 방법
public class URLLoader {
	//지정된 url과 연결하여 스트림을 생성해준다
	//따라서 개발자가 스트림을 이용하여 실행중인 프로그램으로 입력처리를 하면 된다
	URL url;
	URLConnection con;
	BufferedReader buffr;
	
	public URLLoader() {
		try {
			url=new URL("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=n6hS8ECnUUUCmKfI7WEwCKsfjIfNeOAT%2F976O8RvtLaAnxVgkx71qqW%2FvsHSTBCeYnk3UPEM6Jlm2WK6NpNfRw%3D%3D&searchWrd=%EB%B6%81%ED%95%9C%EC%82%B0");
			con=url.openConnection();
			InputStream is=con.getInputStream();
			buffr=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String data=null;
			
			while(true) {
				data=buffr.readLine();
				System.out.println("data: "+data);
				if(data==null)break;
				System.out.println(data);		
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr!=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new URLLoader();
	}
}
