package com.stock.control.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StockistOrder.class)
public class StockistOrder_ {
	public static volatile SingularAttribute<StockistOrder, Stockist> stockist;
	public static volatile SingularAttribute<StockistOrder, Date> dateOrder;
}
