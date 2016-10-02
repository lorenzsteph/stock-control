package com.stock.control.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Storehouse;

public interface StorehouseRepository extends CrudRepository<Storehouse, Long>, JpaSpecificationExecutor<Storehouse> {

}
