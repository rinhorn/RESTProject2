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
 * ���� �ۼ����� ��Ʈ�ѷ��� Ŭ���̾�Ʈ�� ������ �������� ������ ����. ��, ���������� �ܸ��� ���� �����ϱ� ���� ����� ���������� ����
 * ������ ���·� �����ؾ� ��(json, xml...)
 * */
@RestController
public class RestMountainController {
	@Autowired
	private MountainService mountainService;
	
	//�������� ������ ���� �ʰ� �����ڰ� ���� Restful �����񽺸� ó���� ��� �����ڰ� ������ json�� �����ؾ���.
	@RequestMapping(value="/rest/mountains2", method=RequestMethod.GET)
	@ResponseBody //ViewResolver�� ���� �ؼ��� ���´�. ��, ��ȯ�� ��ü�� ���� ������ ��ü�� �Ǿ� �� ��ü�� �����Ѵ�.
	public String selectAll() {
		System.out.println("�� ��� ������ ����?");
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
	
	//������ ������ �����ڵ��� ������ POJO�� json, xml ���� ��ȯ �۾�(converting)�� ó���ؾ� �ϴµ� �� ������ �������� �ڵ����� ó���� �� �� �ִ�.
	@RequestMapping(value="/rest/mountains", method=RequestMethod.GET)
	public List selectAllByRest() {
		List<Mountain> mtList=mountainService.selectAll();
		
		return mtList;
	}

}
