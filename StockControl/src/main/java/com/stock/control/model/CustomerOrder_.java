package com.stock.control.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerOrder.class)
public class CustomerOrder_ {
	public static volatile SingularAttribute<CustomerOrder, Customer> customer;
	public static volatile SingularAttribute<CustomerOrder, Date> dateOrder;
}
