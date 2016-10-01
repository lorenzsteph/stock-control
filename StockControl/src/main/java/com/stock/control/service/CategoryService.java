package com.stock.control.service;

import java.util.List;

import com.stock.control.model.LinkBrandCategory;

public interface CategoryService {

	List<LinkBrandCategory> findCategoryByIdBrand(long idBrand);

}
