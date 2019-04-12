package com.itbank.common.file;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileManager {
	
	
	//���ϸ� �� Ȯ���� �����Ͽ� ��ȯ�ϴ� �޼���
	public String getExt(String path) {
		int lastIndex=path.lastIndexOf(".");
	
		return path.substring(lastIndex+1,path.length());
	}

	//������ ��ο� ������ �����ϴ� �޼���(������ �̿ܿ��� �������� ����)
	public boolean save() {
		return false;
	}
	
	//���ϸ� �ٲٱ� : ������ ���ο� ���ϸ��� ��¥�� �̿��Ѵ�.
	public boolean renameByDate(File oriFile, String ext) {
		System.out.println("�Ѱܹ��� ������ Ǯ ��δ� "+oriFile.getAbsolutePath());
		String fullPath=oriFile.getAbsolutePath();
		
		long time=System.currentTimeMillis();
		
		oriFile.renameTo(new File(""));
		
		return false;
	}
	
	/*
	public static void main(String[] args) {
		FileManager fm=new FileManager();
		System.out.println(fm.getExt("�̾ƾƿ��.png"));
	}
	*/
}
