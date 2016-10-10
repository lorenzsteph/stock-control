package com.stock.control;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.configuration.JPAConfiguration;
import com.stock.control.configuration.MvcConfiguration;
import com.stock.control.dao.BrandRepository;
import com.stock.control.dao.CategoryRepository;
import com.stock.control.dao.ProductRepository;
import com.stock.control.model.Brand;
import com.stock.control.model.Category;
import com.stock.control.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, MvcConfiguration.class })
@WebAppConfiguration
public class ProductRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void deleteProduct() {
		Product product = new Product();
		Brand brand = brandRepository.findOne(2L);
		Category category = categoryRepository.findOne(3L);
		product.setBrand(brand);
		product.setCategory(category);
		product = productRepository.save(product);
		log.debug("delete product : " + product.getIdProduct());
		productRepository.delete(product.getIdProduct());

		
		Product f = productRepository.findOne(product.getIdProduct());
		Assert.assertNull(f);
	}

	@Test
	public void deleteIdProduct() {
		productRepository.delete(381L);

		
		Product f = productRepository.findOne(381L);
		Assert.assertNull(f);
	}
	
}
