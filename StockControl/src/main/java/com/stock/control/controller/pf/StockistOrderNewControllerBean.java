package com.stock.control.controller.pf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardColumn; 
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.StorehouseLazyListDataModel;
import com.stock.control.controller.pf.helper.StockistNewOrderHelperBean;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.Stockist;
import com.stock.control.model.StockistOrder;
import com.stock.control.model.StockistOrderProduct;
import com.stock.control.model.StockistOrderProductNew;
import com.stock.control.model.Storehouse;
import com.stock.control.service.ProductService;
import com.stock.control.service.StockistOrderService;
import com.stock.control.service.StorehouseService;
import com.stock.control.utils.ConstantStock;

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

	@Autowired
	private StockistNewOrderHelperBean stockistNewOrderHelper;

	private StockistOrderProductNew modelStockistOrderProductNew;

	private Storehouse selectedStorehouse;

	private Stockist selectedStockist;

	private StorehouseLazyListDataModel storehouseDataModel;

	private List<ProductOrder> cart;

	private StockistOrder stockistOrder;

	private DashboardModel model;

	@PostConstruct
	public void initBean() {
		initOrder();
		initDashboard();
	}

	private void initDashboard() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("stockist");
		column2.addWidget("order");
		column3.addWidget("cart");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);

		log.debug("dashboard initialized!!!");
	}

	private void initOrder() {
		emptyCart();
		selectedStorehouse = null;
		stockistOrder = new StockistOrder();
		selectedStockist = new Stockist();
		modelStockistOrderProductNew = new StockistOrderProductNew();

		log.debug("order initialized!!!");
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

	public void onStockistRowSelect(SelectEvent event) {
		Stockist stockist = (Stockist) event.getObject();
		FacesMessage msg = new FacesMessage("Stockist Selected", stockist.getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setStockist(stockist);
		storehouseDataModel = new StorehouseLazyListDataModel(storehouseService, stockist);

	}

	public void addCart() {
		log.debug("addCart");
		Long idProduct = selectedStorehouse.getIdProduct();
		add(selectedStorehouse, idProduct);
		FacesMessage msg = new FacesMessage("Storehouse product selected", selectedStorehouse.getProduct());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		modelStockistOrderProductNew = new StockistOrderProductNew();
	}

	public void saveCart() {
		if (selectedStockist == null) {
			FacesMessage msg = new FacesMessage("No stockist selected!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		stockistOrderService.saveOrder(cart, stockistOrder, selectedStockist);

		log.debug("cart saved...empties");
		initOrder();

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
			if (productOrder.getProduct().getIdProduct().equals(idProduct)) {
				productOrder.setAmount(productOrder.getAmount() + modelStockistOrderProductNew.getAmount().intValue());
				productOrder.setPrice(modelStockistOrderProductNew.getPriceIva());
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
		e.setAmount(modelStockistOrderProductNew.getAmount().intValue());
		e.setProduct(productService.findProduct(storehouse.getIdProduct()));
		e.setPrice(modelStockistOrderProductNew.getPriceIva());
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

		navigationBean.goToPageSelected(ConstantStock.URL_NEW_STOKIST_ORDER);
	}

	public void newStockistOrder() {
		log.debug("call go to new stockist order!");
		selectedRecordBean.reset();
		initOrder();
		navigationBean.goToPageSelected(ConstantStock.URL_NEW_STOKIST_ORDER);

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

	public void reloadPriceIva(AjaxBehaviorEvent event) {
		log.info("call to calculate price for order!");
		stockistNewOrderHelper.calculatePrice(modelStockistOrderProductNew);
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

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	public StockistOrderProductNew getModelStockistOrderProductNew() {
		return modelStockistOrderProductNew;
	}

	public void setModelStockistOrderProductNew(StockistOrderProductNew modelStockistOrderProductNew) {
		this.modelStockistOrderProductNew = modelStockistOrderProductNew;
	}

}
