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
import com.stock.control.model.Fornitore;

@Service
public class FornitoreDaoBean extends AbstractDaoBean implements FornitoreDao {

	public int delete(int id) {
		String sql = "DELETE FROM FORNITORE WHERE ID_FORNITORE=:ID_FORNITORE";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_FORNITORE", id);

		return getNamedParameterJdbcTemplate().update(sql, maps);
	}

	@Override
	public int saveOrUpdate(Fornitore fornitore) {
		int result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_FORNITORE", fornitore.getIdFornitore());
		maps.put("DESCRIZIONE", fornitore.getDescrizione());

		if (fornitore.getIdFornitore() > 0) {
			// update
			String sql = "UPDATE FORNITORE SET DESCRIZIONE = :DESCRIZIONE WHERE ID_FORNITORE = :ID_FORNITORE";

			result = getNamedParameterJdbcTemplate().update(sql, maps);
		} else {
			// insert
			String sql = "INSERT INTO FORNITORE (DESCRIZIONE)" + " VALUES (:DESCRIZIONE)";
			result = getNamedParameterJdbcTemplate().update(sql, maps);
		}
		return result;
	}

	@Override
	public Fornitore getFornitore(int idFornitore) {
		String sql = "SELECT * FROM FORNITORE WHERE ID_FORNITORE = :ID_FORNITORE";

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("ID_FORNITORE", idFornitore);

		return getNamedParameterJdbcTemplate().query(sql, maps, new ResultSetExtractor<Fornitore>() {

			@Override
			public Fornitore extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Fornitore fornitore = new Fornitore();
					fornitore.setIdFornitore(rs.getInt("ID_FORNITORE"));
					fornitore.setDescrizione(rs.getString("DESCRIZIONE"));
					return fornitore;
				}

				return null;
			}

		});
	}

	@Override
	public List<Fornitore> getAllFornitore() {
		String sql = "SELECT * FROM FORNITORE ";
		List<Fornitore> listContact = getNamedParameterJdbcTemplate().query(sql, new RowMapper<Fornitore>() {

			@Override
			public Fornitore mapRow(ResultSet rs, int rowNum) throws SQLException {
				Fornitore fornitore = new Fornitore();
				fornitore.setIdFornitore(rs.getInt("ID_FORNITORE"));
				fornitore.setDescrizione(rs.getString("DESCRIZIONE"));
				return fornitore;
			}

		});

		return listContact;
	}

}
