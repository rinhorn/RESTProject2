package com.itbank.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.common.exception.DataNotFoundFailException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.common.file.FileManager;
import com.itbank.model.domain.Mountain;
import com.itbank.model.service.MountainService;

//��� ���õ� ��� ó���� ����ϴ� ��Ʈ�ѷ�
@Controller
public class MountainController {
	@Autowired
	private MountainService mountainService;
	
	@Autowired
	private FileManager fileManager;
	
	//������ ����� �� ���� ��ȸ
	@RequestMapping(value="/admin/mountain/list", method=RequestMethod.GET)
	@ResponseBody
	public String getList(@RequestParam("name") String name) {
		List<Mountain> mtList=mountainService.getList(name);
		
		//�ڹ� ��ü�� json���� ��ȯ�Ͽ� Ŭ���̾�Ʈ�� ����
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<mtList.size();i++) {
			Mountain mt=mtList.get(i);
			
			JSONObject obj=new JSONObject();
			obj.put("addr", mt.getAddr());
			obj.put("detail", mt.getDetail());
			jsonArray.add(obj);
		}
		return jsonArray.toString();
	}
	
	//�� ���� ���
	@RequestMapping(value="/admin/mountain/regist", method=RequestMethod.POST)
	//@ResponseBody �񵿱������� �Ȱ��Ƿ� �� �ʿ����
	public String regist(Mountain mountain, HttpServletRequest request) {
		MultipartFile myFile=mountain.getMyFile();
		//myFile.get
		
		String filename=myFile.getOriginalFilename();
		System.out.println("���ϸ��� "+filename);
		String realPath=request.getServletContext().getRealPath("/data");
		
		System.out.println(realPath);
		File uploadFile=null;
		
		try {
			uploadFile=new File(realPath+"/"+filename);//�̷��� ������� ���ϳ���
			String ext=fileManager.getExt(filename);
			fileManager.renameByDate(uploadFile, ext);
			
			myFile.transferTo(new File(realPath+"/"+filename));
			mountain.setFilename(filename);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*
		InputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=myFile.getInputStream();
			String realPath=request.getServletContext().getRealPath("/data");
			new FileOutputStream(realPath);
			
			int data=-1;
			while(true) {
				data=fis.read();
				if(data==-1)break;
				fos.write(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	
		mountainService.insert(mountain);
		
		
		return "redirect:/admin/mountain/mtlist";
	}
	
	//�� ��� ����
	@RequestMapping(value="/admin/mountain/mtlist",method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav=new ModelAndView("admin/map/list");
		
		return mav;
	}
	
	@ExceptionHandler(DataNotFoundFailException.class)
	@ResponseBody
	public String getListFail() {
		return null;
	}
	
	@ExceptionHandler(RegistFailException.class)
	public ModelAndView registFail(RegistFailException e) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("err",e);
		mav.setViewName("admin/error/errorpage");
		
		return mav;
	}
}
