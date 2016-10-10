package com.stock.control.model;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Storehouse_ {
	public static volatile SingularAttribute<Storehouse, String> stockist;
	public static volatile SingularAttribute<Storehouse, String> brand;
	public static volatile SingularAttribute<Storehouse, String> category;
	public static volatile SingularAttribute<Storehouse, String> codProduct;
	public static volatile SingularAttribute<Storehouse, String> product;
	public static volatile SingularAttribute<Storehouse, String> range;

	public static volatile SingularAttribute<Storehouse, BigDecimal> sellingPrice;
	public static volatile SingularAttribute<Storehouse, BigDecimal> priceOrder;
	public static volatile SingularAttribute<Storehouse, BigDecimal> storeTotal;
	public static volatile SingularAttribute<Storehouse, BigDecimal> storePrice;
	
	public static volatile SingularAttribute<Storehouse, BigDecimal> idProductForOrder;

}
