package com.stock.control.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.StockistOrder;

public interface StockistOrderRepository extends CrudRepository<StockistOrder, Long>, JpaSpecificationExecutor<StockistOrder> {

}
