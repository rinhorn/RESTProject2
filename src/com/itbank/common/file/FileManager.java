package com.itbank.common.file;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileManager {
	
	
	//파일명 중 확장자 추출하여 반환하는 메서드
	public String getExt(String path) {
		int lastIndex=path.lastIndexOf(".");
	
		return path.substring(lastIndex+1,path.length());
	}

	//지정한 경로에 파일을 저장하는 메서드(스프링 이외에서 써먹으라고 놔둠)
	public boolean save() {
		return false;
	}
	
	//파일명 바꾸기 : 원리는 새로운 파일명을 날짜를 이용한다.
	public boolean renameByDate(File oriFile, String ext) {
		System.out.println("넘겨받은 파일의 풀 경로는 "+oriFile.getAbsolutePath());
		String fullPath=oriFile.getAbsolutePath();
		
		long time=System.currentTimeMillis();
		
		oriFile.renameTo(new File(""));
		
		return false;
	}
	
	/*
	public static void main(String[] args) {
		FileManager fm=new FileManager();
		System.out.println(fm.getExt("이아아우아.png"));
	}
	*/
}
