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

//산과 관련된 모든 처리를 감당하는 컨트롤러
@Controller
public class MountainController {
	@Autowired
	private MountainService mountainService;

	@Autowired
	private FileManager fileManager;

// 관리자 모드의 산정보 조회 요청
	@RequestMapping(value = "/admin/mountain/list", method = RequestMethod.GET)
	@ResponseBody
	public String getList(@RequestParam("name") String name) {
		List<Mountain> mtList = mountainService.getList(name);

		// 자바 객체를 json으로 변환하여 클라이언트에 전송
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < mtList.size(); i++) {
			Mountain mt = mtList.get(i);
			JSONObject obj = new JSONObject();

			obj.put("addr", mt.getAddr());
			obj.put("detail", mt.getDetail());
			jsonArray.add(obj);
		}

		return jsonArray.toString();
	}

// 산 정보 등록
	@RequestMapping(value = "/admin/mountain/regist", method = RequestMethod.POST)
	public String regist(Mountain mountain, HttpServletRequest request) {
		MultipartFile myFile = mountain.getMyFile();
		String filename = myFile.getOriginalFilename();
		System.out.println("파일명은 " + filename);

		String realPath = request.getServletContext().getRealPath("/data");
		System.out.println(realPath);

		File uploadFile = null;
		try {
			uploadFile = new File(realPath + "/" + filename);
			// 업로드
			myFile.transferTo(uploadFile);

			// 업로드된 파일명 교체
			filename = fileManager.renameByDate(uploadFile, realPath);
			if (filename != null) {
				mountain.setFilename(filename);
				mountainService.insert(mountain);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/admin/mountain/mtList";
	}

// 산 목록 가져오기
	@RequestMapping(value = "/admin/mountain/mtList", method = RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView("/admin/map/list");
		return mav;
	}

	@ExceptionHandler(DataNotFoundFailException.class)
	@ResponseBody
	public String getListFail() {
		return null;
	}

	@ExceptionHandler(RegistFailException.class)
	public ModelAndView registFail(RegistFailException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("err", e);
		mav.setViewName("admin/error/errorPage");
		return mav;
	}
}