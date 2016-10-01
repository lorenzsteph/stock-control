package com.stock.control.service;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.LinkCategoryProductSearchFilter;
import com.stock.control.model.LinkCategoryProduct;

public interface ProductService {

	public Page<LinkCategoryProduct> findProductByIdCategory(LinkCategoryProductSearchFilter filter, int pageNumber, int pageSize);

}
