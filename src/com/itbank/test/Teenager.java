package com.itbank.test;

import org.springframework.stereotype.Component;

@Component  //�з��� �ȵ� �� ���� ū ���� ���
public class Teenager implements Student{

	public void getUp() {
		System.out.println("����մϴ�");
	}

	public void goSchool() {
		System.out.println("��մϴ�");
	}

	public void study() {
		System.out.println("�����մϴ�");
	}
}
