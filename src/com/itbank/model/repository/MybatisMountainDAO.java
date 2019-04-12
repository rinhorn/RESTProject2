package com.itbank.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Mountain;

@Repository
public class MybatisMountainDAO implements MountainDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public List selectAll() {
		return null;
	}

	public Mountain select(int mountain_id) {
		return null;
	}

	public int insert(Mountain mountain) {
		return sessionTemplate.insert("Mountain.insert",mountain);
	}

	public int update(Mountain mountain) {
		return 0;
	}

	public int delete(int mountain_id) {
		return 0;
	}

	
}
