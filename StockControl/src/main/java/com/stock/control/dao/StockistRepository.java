package com.stock.control.dao;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Stockist;

public interface StockistRepository extends CrudRepository<Stockist, Long> {

}
