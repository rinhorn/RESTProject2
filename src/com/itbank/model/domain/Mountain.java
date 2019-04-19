package com.itbank.model.domain;

import org.springframework.web.multipart.MultipartFile;

public class Mountain {
	private int mountain_id;
	private String name;
	private String addr;
	private String detail;
	private String filename;
	//스프링에서 파일을 처리할 수 있는 파일처리자 등록
	private MultipartFile myFile;//여러건의 사진을 넣고 싶다면 배열로 가면된다 MultipartFile[]
	private double lati;
	private double longi;
	private String marker;
	
	public int getMountain_id() {
		return mountain_id;
	}
	public void setMountain_id(int mountain_id) {
		this.mountain_id = mountain_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public MultipartFile getMyFile() {
		return myFile;
	}
	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}
	public double getLati() {
		return lati;
	}
	public void setLati(double lati) {
		this.lati = lati;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	
}
