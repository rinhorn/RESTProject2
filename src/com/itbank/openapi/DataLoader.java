package com.itbank.openapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DataLoader {

	public static void main(String[] args) throws Exception{
		  StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=n6hS8ECnUUUCmKfI7WEwCKsfjIfNeOAT%2F976O8RvtLaAnxVgkx71qqW%2FvsHSTBCeYnk3UPEM6Jlm2WK6NpNfRw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode("���ѻ�", "UTF-8")); /*������ID*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	}
}
