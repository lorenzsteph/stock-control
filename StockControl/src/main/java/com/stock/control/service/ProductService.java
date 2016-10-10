package com.stock.control.service;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.ProductSearchFilter;
import com.stock.control.model.Product;

public interface ProductService {

	public Page<Product> findProductByIdCategory(ProductSearchFilter filter, int pageNumber, int pageSize);

	public Product saveProduct(Product product);

	public void deleteProduct(long product);

	public Product findProduct(long idProduct);

}
