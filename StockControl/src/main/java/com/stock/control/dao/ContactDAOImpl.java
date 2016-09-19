package com.stock.control.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.stock.control.dao.util.DatabaseDaoInitializer;

public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private DatabaseDaoInitializer database;

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM contact WHERE contact_id=?";
		database.getJdbcTemplate().update(sql, contactId);
	}

}
