package com.itbank.test;

import org.springframework.stereotype.Component;

@Component  //분류가 안될 때 가장 큰 범위 사용
public class Teenager implements Student{

	public void getUp() {
		System.out.println("기상합니다");
	}

	public void goSchool() {
		System.out.println("등교합니다");
	}

	public void study() {
		System.out.println("공부합니다");
	}
}
