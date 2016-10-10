package com.stock.control.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.CustomerOrderRepository;
import com.stock.control.dao.LinkOrderRepository;
import com.stock.control.dao.StockistOrderProductRepository;
import com.stock.control.dao.dynamic.command.CustomerOrderQueryCommand;
import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.model.Customer;
import com.stock.control.model.CustomerOrder;
import com.stock.control.model.LinkOrder;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.StockistOrderProduct;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CustomerOrderServiceBean implements CustomerOrderService {

	private static final Logger log = LoggerFactory.getLogger(CustomerOrderServiceBean.class);

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	@Autowired
	private LinkOrderRepository linkOrderRepository;

	@Autowired
	private StockistOrderProductRepository stockistOrderProductRepository;

	@Override
	public Page<CustomerOrder> findCustomerOrderFilter(CustomerOrderSearchFilter customerOrderFilter, int pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(customerOrderFilter.getOrder()));
		CustomerOrderQueryCommand command = new CustomerOrderQueryCommand();

		return customerOrderRepository.findAll(command.where(customerOrderFilter), request);
	}

	@Override
	public void saveOrder(List<ProductOrder> cart, CustomerOrder customerOrder, Customer selectedCustomer) {
		customerOrder.setCustomer(selectedCustomer);
		log.debug("save customer order in service");

		customerOrder = customerOrderRepository.save(customerOrder);
		log.debug("customer order id : " + customerOrder.getIdCustomerOrder());
		LinkOrder linkOrder = null;
		List<LinkOrder> linkOrderForUpdate = new ArrayList<LinkOrder>();
		BigDecimal priceOrder = BigDecimal.ZERO;

		for (ProductOrder productOrder : cart) {

			for (int i = 0; productOrder.getAmount() > i; i++) {

				Set<StockistOrderProduct> allStock = stockistOrderProductRepository.finfMinIdForIdProduct(productOrder.getProduct().getIdProduct());
				log.debug("allStock size : " + allStock.size());
				for (StockistOrderProduct stockistOrderProduct : allStock) {
					LinkOrder lo = linkOrderRepository.findByStockistOrderProductId(stockistOrderProduct.getIdStockistOrderProduct());
					if (lo != null) {
						continue;
					}
					linkOrder = new LinkOrder();
					linkOrder.setCustomerOrder(customerOrder);
					linkOrder.setStockistOrderProduct(stockistOrderProduct);
					log.debug("linkOPrder : " + linkOrder.toString());
					linkOrder = linkOrderRepository.save(linkOrder);

					priceOrder = priceOrder.add(stockistOrderProduct.getProduct().getSellingPrice());
					linkOrderForUpdate.add(linkOrder);
					break;
				}
			}
		}

		customerOrder.setPriceOrder(priceOrder);
		customerOrder.setDiscountPercentage(calculatePercentage(customerOrder.getRealOrderPrice(), priceOrder));
		customerOrder = customerOrderRepository.save(customerOrder);

		for (LinkOrder linkOrderUpd : linkOrderForUpdate) {
			linkOrderUpd.setRealSellingPrice(calculateRealSellingPrice(linkOrderUpd.getStockistOrderProduct().getProduct().getSellingPrice(), customerOrder.getDiscountPercentage()));
			linkOrderRepository.save(linkOrder);
		}

	}

	private BigDecimal calculateRealSellingPrice(BigDecimal price, BigDecimal discountPercentage) {

		BigDecimal result = price.subtract(price.multiply(discountPercentage).divide(new BigDecimal("100")));
		log.debug("calcualte percentage discont for product : price [" + price + "] * discountPercentage [" + discountPercentage + "] / 100 = " + result);
		return price.min(result);
	}

	private BigDecimal calculatePercentage(BigDecimal realOrderPrice, BigDecimal priceOrder) {
		BigDecimal result = new BigDecimal("100").subtract(realOrderPrice.multiply(new BigDecimal("100")).divide(priceOrder, 2));
		log.debug("calcualte percentage discont : realOrderPrice  [" + realOrderPrice + "] * 100 / priceOrder [" + priceOrder + "] = " + result);
		return result;
	}

	@Override
	public void removeCustomerOrder(CustomerOrder selectedCustomerOrder) {
		for (LinkOrder lo : selectedCustomerOrder.getLinkOrder()) {
			linkOrderRepository.delete(lo.getIdLinkOrder());
		}
		customerOrderRepository.delete(selectedCustomerOrder.getIdCustomerOrder());

	}

}
