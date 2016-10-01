package com.stock.control.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_{
	public static volatile SingularAttribute<Product, Long> idProduct;
}
