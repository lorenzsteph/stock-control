package com.stock.control.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.StockistOrderProduct;

public interface StockistOrderProductRepository extends CrudRepository<StockistOrderProduct, Long>, JpaSpecificationExecutor<StockistOrderProduct> {

	@Query("SELECT l FROM StockistOrderProduct l join l.product p left join l.linkOrder lo where p.idProduct = :idProduct and lo.idLinkOrder IS NULL order by l.idStockistOrderProduct")
	public Set<StockistOrderProduct> finfMinIdForIdProduct(@Param("idProduct") Long idProduct);
}
