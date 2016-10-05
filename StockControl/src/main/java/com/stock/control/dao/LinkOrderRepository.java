package com.stock.control.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.LinkOrder;

public interface LinkOrderRepository extends CrudRepository<LinkOrder, Long> {

	@Query("SELECT l FROM LinkOrder l join l.stockistOrderProduct s where s.idStockistOrderProduct = :idStockistOrderProduct")
	public LinkOrder findByStockistOrderProductId(@Param("idStockistOrderProduct") Long idStockistOrderProduct);

}
