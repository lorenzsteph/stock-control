package com.stock.control.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Stockist.class)
public class Stockist_ {
	public static volatile SingularAttribute<Stockist, String> descr;
	public static volatile SingularAttribute<Stockist, Long> idStockist;
}
