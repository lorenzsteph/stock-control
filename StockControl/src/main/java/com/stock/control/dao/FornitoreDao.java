package com.stock.control.dao;

import java.util.List;

import com.stock.control.model.Fornitore;

public interface FornitoreDao {

	public int saveOrUpdate(Fornitore f);

	public int delete(int idFornitore);

	public Fornitore getFornitore(int idFornitore);

	public List<Fornitore> getAllFornitore();
}
