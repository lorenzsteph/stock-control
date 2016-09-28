package com.stock.control.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, String> descr;
	public static volatile SingularAttribute<Customer, Long> idCustomer;
}
