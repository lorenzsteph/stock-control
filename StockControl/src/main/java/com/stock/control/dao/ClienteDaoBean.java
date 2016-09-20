package com.stock.control.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.stock.control.dao.util.AbstractDaoBean;
import com.stock.control.model.Cliente;

@Service
public class ClienteDaoBean extends AbstractDaoBean implements ClienteDao {

	public int delete(int id) {
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE=:ID_CLIENTE";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CLIENTE", id);

		return getNamedParameterJdbcTemplate().update(sql, maps);
	}

	@Override
	public int saveOrUpdate(Cliente cliente) {
		int result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CLIENTE", cliente.getIdCliente());
		maps.put("DESCRIZIONE", cliente.getDescrizione());

		if (cliente.getIdCliente() > 0) {
			// update
			String sql = "UPDATE CLIENTE SET DESCRIZIONE = :DESCRIZIONE WHERE ID_CLIENTE = :ID_CLIENTE";
			result = getNamedParameterJdbcTemplate().update(sql, maps);
		} else {
			// insert
			String sql = "INSERT INTO CLIENTE (DESCRIZIONE)" + " VALUES (:DESCRIZIONE)";
			result = getNamedParameterJdbcTemplate().update(sql, maps);
		}

		return result;

	}

	@Override
	public Cliente getCliente(int idCliente) {
		String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = :ID_CLIENTE";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_CLIENTE", idCliente);

		return getNamedParameterJdbcTemplate().query(sql, maps, new ResultSetExtractor<Cliente>() {

			@Override
			public Cliente extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
					cliente.setDescrizione(rs.getString("DESCRIZIONE"));
					return cliente;
				}

				return null;
			}

		});

	}

	@Override
	public List<Cliente> getAllCliente() {
		String sql = "SELECT * FROM CLIENTE ";
		List<Cliente> listContact = getNamedParameterJdbcTemplate().query(sql, new RowMapper<Cliente>() {

			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
				cliente.setDescrizione(rs.getString("DESCRIZIONE"));

				return cliente;
			}

		});

		return listContact;
	}

}
