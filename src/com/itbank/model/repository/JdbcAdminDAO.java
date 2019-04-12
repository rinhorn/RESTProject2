package com.itbank.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Admin;

@Repository
public class JdbcAdminDAO implements AdminDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List selectAll() {
		return null;
	}

	public Admin select(int admin_id) {
		return null;
	}

	public Admin loginCheck(Admin admin) {
		Admin obj=null;
		String sql="select * from admin where id=? and pass=?";
		try {  //스프링에서 강요하지는 않지만 개발자가 잡아야 하는 예외
			obj=jdbcTemplate.queryForObject(sql, new RowMapper<Admin>() {
				public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
					Admin admin=new Admin();
					admin.setAdmin_id(rs.getInt("admin_id"));
					admin.setId(rs.getString("id"));
					admin.setPass(rs.getString("pass"));
					admin.setName(rs.getString("name"));
					System.out.println("dao에서의 결과 "+admin.getId());
					return admin;
				}
			}, admin.getId(), admin.getPass());
		} catch (DataAccessException e) {  //스프링에서의 최상위 예외 객체
			e.printStackTrace();
		}
		return obj;
	}

	public int insert(Admin admin) {
		return 0;
	}

	public int update(Admin admin) {
		return 0;
	}

	public int delete(int admin_id) {
		return 0;
	}
}
