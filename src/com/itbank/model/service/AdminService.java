package com.itbank.model.service;

import java.util.List;

import com.itbank.model.domain.Admin;

public interface AdminService {
	public List selectAll();
	public Admin select(int admin_id);
	public Admin loginCheck(Admin admin);
	public void insert(Admin admin);
	public void update(Admin admin);
	public void delete(int admin_id);
}
