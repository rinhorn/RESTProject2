package com.itbank.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itbank.model.domain.Mountain;
import com.itbank.model.service.MountainService;

/*
 * 현재 작성중인 컨트롤러는 클라이언트의 유형이 무엇인지 관심이 없다. 즉, 여러종류의 단말기 등을 지원하기 위해 기술에 종속적이지 않은
 * 데이터 형태로 전송해야 함(json, xml...)
 * */
@RestController
public class RestMountainController {
	@Autowired
	private MountainService mountainService;
	
	//스프링의 지원을 받지 않고 개발자가 직접 Restful 웹서비스를 처리할 경우 개발자가 일일이 json을 생성해야함.
	@RequestMapping(value="/rest/mountains2", method=RequestMethod.GET)
	@ResponseBody //ViewResolver에 의한 해석을 막는다. 즉, 반환값 자체를 전송 데이터 몸체에 실어 그 자체를 전송한다.
	public String selectAll() {
		System.out.println("산 목록 데이터 원함?");
		List<Mountain> mtList=mountainService.selectAll();
		
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<mtList.size();i++) {
			Mountain mt=mtList.get(i);
			JSONObject json=new JSONObject();
			json.put("mountain_id", mt.getMountain_id());
			json.put("name", mt.getName());
			json.put("addr", mt.getAddr());
			json.put("detail", mt.getDetail());
			json.put("filename", mt.getFilename());
			json.put("lati", mt.getLati());
			json.put("longi", mt.getLongi());
			json.put("marker", mt.getMarker());
			
			jsonArray.add(json);
		}
		
		return jsonArray.toString();
	}
	
	//웹서비스 구현시 개발자들이 일일이 POJO와 json, xml 간에 변환 작업(converting)을 처리해야 하는데 이 과정을 스프링이 자동으로 처리해 줄 수 있다.
	@RequestMapping(value="/rest/mountains", method=RequestMethod.GET)
	public List selectAllByRest() {
		List<Mountain> mtList=mountainService.selectAll();
		
		return mtList;
	}

}
