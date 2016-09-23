package com.stock.control.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.stock.control.dao.util.AbstractDaoBean;
import com.stock.control.model.Customer;

@Service
public class CustomerDaoBean extends AbstractDaoBean implements CustomerDao {

	private static final Logger log = LoggerFactory.getLogger(CustomerDaoBean.class);

	public int delete(int id) {
		Customer c = this.getCustomer(id);
		c.setDateEndValidity(new Date());
		return this.saveOrUpdate(c);
	}

	public int phisicalDelete(int id) {
		String sql = "DELETE FROM CUSTOMER WHERE ID_CUSTOMER=:ID_CUSTOMER";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CUSTOMER", id);
		log.debug("delete sql : " + sql + " - masp : " + maps);
		return getNamedParameterJdbcTemplate().update(sql, maps);
	}

	@Override
	public int saveOrUpdate(Customer customer) {
		int result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CUSTOMER", customer.getIdCustomer());
		maps.put("DESCR", customer.getDescr());
		maps.put("DATE_END_VALIDITY", customer.getDateEndValidity());

		if (customer.getIdCustomer() > 0) {
			// update
			String sql = "UPDATE CUSTOMER SET DESCR = :DESCR, DATE_END_VALIDITY = :DATE_END_VALIDITY WHERE ID_CUSTOMER = :ID_CUSTOMER";
			log.debug("saveOrUpdate sql : " + sql);
			result = getNamedParameterJdbcTemplate().update(sql, maps);
		} else {
			// insert
			String sql = "INSERT INTO CUSTOMER (DESCR)" + " VALUES (:DESCR)";
			log.debug("saveOrUpdate sql : " + sql);
			result = getNamedParameterJdbcTemplate().update(sql, maps);
		}

		return result;

	}

	@Override
	public Customer getCustomer(int idCustomer) {
		String sql = "SELECT * FROM CUSTOMER WHERE ID_CUSTOMER = :ID_CUSTOMER";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CUSTOMER", idCustomer);

		return getNamedParameterJdbcTemplate().query(sql, maps, new ResultSetExtractor<Customer>() {

			@Override
			public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Customer customer = new Customer();
					customer.setIdCustomer(rs.getInt("ID_CUSTOMER"));
					customer.setDescr(rs.getString("DESCR"));
					customer.setDateEndValidity(rs.getDate("DATE_END_VALIDITY"));
					return customer;
				}

				return null;
			}

		});

	}

	@Override
	public List<Customer> getAllCustomer() {
		String sql = "SELECT * FROM CUSTOMER ORDER BY ID_CUSTOMER";
		List<Customer> listContact = getNamedParameterJdbcTemplate().query(sql, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer();

				customer.setIdCustomer(rs.getInt("ID_CUSTOMER"));
				customer.setDescr(rs.getString("DESCR"));
				customer.setDateEndValidity(rs.getDate("DATE_END_VALIDITY"));
				return customer;
			}

		});

		return listContact;
	}

}
