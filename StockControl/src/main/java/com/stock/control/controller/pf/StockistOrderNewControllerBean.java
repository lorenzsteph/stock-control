package com.stock.control.controller.pf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.StorehouseLazyListDataModel;
import com.stock.control.model.Stockist;
import com.stock.control.model.StockistOrder;
import com.stock.control.model.StockistOrderProduct;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.Storehouse;
import com.stock.control.service.StockistOrderService;
import com.stock.control.service.ProductService;
import com.stock.control.service.StorehouseService;

@Component(value = "stockistOrderNewCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StockistOrderNewControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(StockistOrderNewControllerBean.class);

	@Autowired
	private StockistOrderService stockistOrderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private StorehouseService storehouseService;

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private NavigationBean navigationBean;

	private Storehouse selectedStorehouse;

	private Stockist selectedStockist;

	private StorehouseLazyListDataModel storehouseDataModel;

	private List<ProductOrder> cart;

	private StockistOrder stockistOrder;

	@PostConstruct
	public void initBean() {
		storehouseDataModel = new StorehouseLazyListDataModel(storehouseService);
		this.emptyCart();
		selectedStorehouse = null;

		stockistOrder = new StockistOrder();
		selectedStockist = new Stockist();

	}

	public void emptyCart() {
		cart = new ArrayList<ProductOrder>();
	}

	public void onRowEdit(RowEditEvent event) {
		log.debug("onRowEdit");
		ProductOrder po = ((ProductOrder) event.getObject());
		addProductCart(po, po.getAmount());

		FacesMessage msg = new FacesMessage("Edit Cancelled", po.getProduct().getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void addCart() {
		log.debug("addCart");
		Long idProduct = selectedStorehouse.getIdProduct();
		add(selectedStorehouse, idProduct);
		FacesMessage msg = new FacesMessage("Storehouse product selected", selectedStorehouse.getProduct());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveCart() {
		if (selectedStockist == null) {
			FacesMessage msg = new FacesMessage("No stockist selected!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		stockistOrderService.saveOrder(cart, stockistOrder, selectedStockist);

		log.debug("cart saved...empties");
		emptyCart();
		stockistOrder = new StockistOrder();
		selectedStockist = new Stockist();

		FacesMessage msg = new FacesMessage("Order saved!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getSumProduct() {
		int total = 0;
		for (ProductOrder productOrder : cart) {
			total += productOrder.getAmount();
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String getOrderPrice() {
		BigDecimal total = calcualteOrderPrice();
		return new DecimalFormat("###,###.###").format(total);
	}

	private BigDecimal calcualteOrderPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (ProductOrder productOrder : cart) {
			total = total.add(productOrder.getProduct().getSellingPrice().multiply(new BigDecimal(productOrder.getAmount())));
		}
		return total;
	}

	private void add(Storehouse storehouse, Long idProduct) {
		boolean productFound = false;
		for (ProductOrder productOrder : cart) {
			if (productOrder.getProduct().getIdProduct() == idProduct) {
				productOrder.setAmount(productOrder.getAmount() + 1);
				productFound = true;
				log.debug("product found in cart, add - " + productOrder.getProduct().getIdProduct() + " - " + productOrder.getAmount());
				break;
			}
		}

		if (!productFound) {
			addCartNewStorehouse(storehouse);
		}
	}

	private void addCartNewStorehouse(Storehouse storehouse) {
		log.debug("product not found create cart and add");
		ProductOrder e = new ProductOrder();
		e.setAmount(1);
		e.setProduct(productService.findProduct(storehouse.getIdProduct()));
		cart.add(e);
		log.debug("product added");
	}

	private void addProductCart(ProductOrder productOrder, int amount) {
		for (ProductOrder po : cart) {
			if (po.getProduct().getIdProduct() == productOrder.getProduct().getIdProduct()) {
				po.setAmount(amount);
				return;
			}
		}
		cart.add(productOrder);
	}

	public void editStockistOrder(StockistOrder selectedStockistOrder) {
		log.debug("edit stockist order: " + selectedStockistOrder.getIdStockistOrder());

		selectedStockist = selectedStockistOrder.getStockist();
		stockistOrder = selectedStockistOrder;
		cart = createCartFromStockistOrder(selectedStockistOrder);

		navigationBean.goToPageSelected("/stock/pf/stockist-order-new.xhtml?faces-redirect=true");
	}

	public void newStockistOrder() {
		this.emptyCart();
		selectedStorehouse = null;

		stockistOrder = new StockistOrder();
		selectedStockist = new Stockist();

		navigationBean.goToPageSelected("/stock/pf/stockist-order-new.xhtml?faces-redirect=true");
	}

	private List<ProductOrder> createCartFromStockistOrder(StockistOrder selectedStockistOrder) {
		List<ProductOrder> result = new ArrayList<ProductOrder>();

		for (StockistOrderProduct lo : selectedStockistOrder.getStockistOrderProduct()) {

			boolean productFound = false;
			for (ProductOrder productOrder : result) {
				if (productOrder.getProduct().getIdProduct() == lo.getProduct().getIdProduct()) {
					productOrder.setAmount(productOrder.getAmount() + 1);
					productFound = true;
					log.debug("product found in cart, add - " + productOrder.getProduct().getIdProduct() + " - " + productOrder.getAmount());
					break;
				}
			}

			if (!productFound) {
				ProductOrder e = new ProductOrder();
				e.setAmount(1);
				e.setProduct(lo.getProduct());
				result.add(e);
			}

		}

		return result;
	}

	public void onRowCancel(RowEditEvent event) {
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

	public List<ProductOrder> getCart() {
		return cart;
	}

	public void setCart(List<ProductOrder> cart) {
		this.cart = cart;
	}

	public Storehouse getSelectedStorehouse() {
		return selectedStorehouse;
	}

	public void setSelectedStorehouse(Storehouse selectedStorehouse) {
		this.selectedStorehouse = selectedStorehouse;
	}

	public StorehouseLazyListDataModel getStorehouseDataModel() {
		return storehouseDataModel;
	}

	public void setStorehouseDataModel(StorehouseLazyListDataModel storehouseDataModel) {
		this.storehouseDataModel = storehouseDataModel;
	}

	public StockistOrder getStockistOrder() {
		return stockistOrder;
	}

	public void setStockistOrder(StockistOrder stockistOrder) {
		this.stockistOrder = stockistOrder;
	}

	public Stockist getSelectedStockist() {
		return selectedStockist;
	}

	public void setSelectedStockist(Stockist selectedStockist) {
		this.selectedStockist = selectedStockist;
	}

}