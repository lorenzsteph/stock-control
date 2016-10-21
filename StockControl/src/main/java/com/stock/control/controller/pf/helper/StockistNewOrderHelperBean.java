package com.stock.control.controller.pf.helper;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stock.control.model.StockistOrderProductNew;

@Service
public class StockistNewOrderHelperBean {

	private static final Logger log = LoggerFactory.getLogger(StockistNewOrderHelperBean.class);
	private static final BigDecimal BIG_DECIMAL_IVA = new BigDecimal("1.22");

	public void calculatePrice(StockistOrderProductNew modelStockistOrderProductNew) {
		BigDecimal priceIva = modelStockistOrderProductNew.getPriceIva();
		if (modelStockistOrderProductNew.getPrice() != null) {
			log.debug("use price");
			priceIva = modelStockistOrderProductNew.getPrice().multiply(BIG_DECIMAL_IVA);

		} else if (modelStockistOrderProductNew.getPriceForAmount() != null) {
			log.debug("use price for amount");
			priceIva = modelStockistOrderProductNew.getPriceForAmount().multiply(BIG_DECIMAL_IVA).divide(modelStockistOrderProductNew.getAmount(), 2);

		} else if (modelStockistOrderProductNew.getPriceForAmountIva() != null) {
			log.debug("use price for amount + iva");
			priceIva = modelStockistOrderProductNew.getPriceForAmountIva().divide(modelStockistOrderProductNew.getAmount(), 2);

		}

		log.debug("calculate price iva : " + priceIva);
		modelStockistOrderProductNew.setPriceIva(priceIva);
	}

}
