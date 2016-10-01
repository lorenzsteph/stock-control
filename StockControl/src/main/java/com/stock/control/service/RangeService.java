package com.stock.control.service;

import java.util.List;

import com.stock.control.model.LinkProductRange;

public interface RangeService {

	List<LinkProductRange> findRangeByIdProduct(long idProduct);

}
