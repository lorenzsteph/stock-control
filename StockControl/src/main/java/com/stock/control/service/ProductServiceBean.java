package com.stock.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.ProductRepository;
import com.stock.control.dao.dynamic.command.ProductQueryCommand;
import com.stock.control.dao.dynamic.filter.ProductSearchFilter;
import com.stock.control.model.Product;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class ProductServiceBean implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Page<Product> findProductByIdCategory(ProductSearchFilter filter, int pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(filter.getOrder()));
		ProductQueryCommand command = new ProductQueryCommand();

		return productRepository.findAll(command.where(filter), request);
	}

}
