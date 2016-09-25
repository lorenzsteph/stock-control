package com.stock.control.dao;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long> {

}
