package com.stock.control.service;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.ProductSearchFilter;
import com.stock.control.model.Product;

public interface ProductService {

	public Page<Product> findProductByIdCategory(ProductSearchFilter filter, int pageNumber, int pageSize);

}
