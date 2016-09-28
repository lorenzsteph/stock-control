package com.stock.control.dao.dynamic.command;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specifications;

public interface QueryCommand<T extends Object, S extends Serializable> {

	public Specifications<S> where(T filter);

}