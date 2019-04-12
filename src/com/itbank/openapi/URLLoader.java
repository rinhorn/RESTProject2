package com.itbank.openapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//���� ������ �ƴ�, ��Ʈ��ũ�� �����ϴ� �ڿ��� �������� ���ø����̼����� �������� ���
public class URLLoader {
	//������ url�� �����Ͽ� ��Ʈ���� �������ش�
	//���� �����ڰ� ��Ʈ���� �̿��Ͽ� �������� ���α׷����� �Է�ó���� �ϸ� �ȴ�
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
