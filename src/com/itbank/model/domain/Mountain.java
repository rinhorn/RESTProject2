package com.itbank.model.domain;

import org.springframework.web.multipart.MultipartFile;

public class Mountain {
	private int mountain_id;
	private String name;
	private String addr;
	private String detail;
	private String filename;
	//���������� ������ ó���� �� �ִ� ����ó���� ���
	private MultipartFile myFile;
	private long lati;
	private long longi;
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
	public long getLati() {
		return lati;
	}
	public void setLati(long lati) {
		this.lati = lati;
	}
	public long getLongi() {
		return longi;
	}
	public void setLongi(long longi) {
		this.longi = longi;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	
}