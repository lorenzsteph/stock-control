package com.stock.control.utils;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class CommonUtils {

	public static int getPageNumber(int start, int pageSize) {

		int pageNum = 0;

		if (start > 0 && pageSize > 0) {
			pageNum = start / pageSize;
		}

		return pageNum;
	}

	public static List<Order> createOrder(SortOrder sortOrder, String sortField) {

		List<Order> order = new ArrayList<Order>();

		Direction dir = Sort.Direction.ASC;
		String strSortOrder = "";

		if (sortOrder != null) {
			strSortOrder = getStrSortOrder(sortOrder);

			dir = Sort.Direction.fromString(strSortOrder);
		}

		Order orderName = new Order(dir, sortField);
		order.add(orderName);

		return order;
	}

	private static String getStrSortOrder(SortOrder sortOrder) {
		if (sortOrder.equals(SortOrder.ASCENDING)) {
			return "ASC";
		} else if (sortOrder.equals(SortOrder.DESCENDING)) {
			return "DESC";
		}
		return null;
	}

}
