package com.stock.control.service;

import java.util.List;

import com.stock.control.model.Cliente;
import com.stock.control.model.Fornitore;

public interface DomainService {

	public Cliente populateDummyCliente(boolean exception) throws Exception;

	public int saveOrUpdateFornitore(Fornitore fornitore);

	public Fornitore getFornitore(int idFornitore);

	public int deleteFornitore(int idFornitore);

	public List<Fornitore> getAllFornitore();

}
