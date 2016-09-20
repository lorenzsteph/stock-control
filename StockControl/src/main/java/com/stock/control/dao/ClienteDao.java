package com.stock.control.dao;

import java.util.List;

import com.stock.control.model.Cliente;

public interface ClienteDao {

	public int saveOrUpdate(Cliente cliente);

	public int delete(int idCliente);

	public Cliente getCliente(int idCliente);

	public List<Cliente> getAllCliente();
}
