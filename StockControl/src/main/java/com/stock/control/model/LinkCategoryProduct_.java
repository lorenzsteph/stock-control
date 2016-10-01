package com.stock.control.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LinkCategoryProduct.class)
public class LinkCategoryProduct_ {
	public static volatile SingularAttribute<LinkCategoryProduct, Category> category;
	public static volatile SingularAttribute<LinkCategoryProduct, Product> product;
}
