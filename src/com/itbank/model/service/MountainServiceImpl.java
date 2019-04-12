package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.DataNotFoundFailException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Mountain;
import com.itbank.model.repository.MountainDAO;
import com.itbank.openapi.MountainParser;

@Service
public class MountainServiceImpl implements MountainService{
	@Autowired
	private MountainDAO mountainDAO;
	private MountainParser mtParser;
	String apiKey="n6hS8ECnUUUCmKfI7WEwCKsfjIfNeOAT%2F976O8RvtLaAnxVgkx71qqW%2FvsHSTBCeYnk3UPEM6Jlm2WK6NpNfRw%3D%3D";
	
	public List getList(String searchWrd) throws DataNotFoundFailException{
		mtParser=new MountainParser();
		List mtList=mtParser.getList(apiKey, searchWrd);
		
		if(mtList==null){
			throw new DataNotFoundFailException("검색 결과가 없습니다");
		}
		return mtList;
	}

	public void insert(Mountain mountain) throws RegistFailException{
		int result=mountainDAO.insert(mountain);
		
		if(result==0) {
			throw new RegistFailException("등록 실패");
		}
	}

	
}
