package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.CustomerOrder;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long>, JpaSpecificationExecutor<CustomerOrder> {

	@Query("SELECT l FROM CustomerOrder l order by dateOrder")
	List<CustomerOrder> findForHomePage();

}
