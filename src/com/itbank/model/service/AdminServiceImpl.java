package com.itbank.model.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.model.domain.Admin;
import com.itbank.model.repository.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	@Qualifier("jdbcAdminDAO")
	private AdminDAO adminDAO;

	public List selectAll() {
		return null;
	}

	public Admin select(int admin_id) {
		return null;
	}

	public Admin loginCheck(Admin admin) throws com.itbank.common.exception.AccountNotFoundException{
		Admin obj=adminDAO.loginCheck(admin);
		if(obj==null) {
			throw new com.itbank.common.exception.AccountNotFoundException("일치하는 정보가 없습니다");
		}
		return obj;
	}

	public void insert(Admin admin) {

	}

	public void update(Admin admin) {

	}

	public void delete(int admin_id) {

	}
}
