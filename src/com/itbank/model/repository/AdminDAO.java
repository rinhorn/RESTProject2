package com.itbank.model.repository;

import java.util.List;

import com.itbank.model.domain.Admin;

public interface AdminDAO {
	public List selectAll();
	public Admin select(int admin_id);
	public Admin loginCheck(Admin admin);
	public int insert(Admin admin);
	public int update(Admin admin);
	public int delete(int admin_id);
}
