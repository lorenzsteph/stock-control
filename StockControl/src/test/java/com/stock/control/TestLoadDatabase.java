package com.stock.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.configuration.JPAConfiguration;
import com.stock.control.configuration.MvcConfiguration;
import com.stock.control.dao.BrandRepository;
import com.stock.control.dao.CategoryRepository;
import com.stock.control.dao.LinkBrandCategoryRepository;
import com.stock.control.dao.LinkCategoryProductRepository;
import com.stock.control.dao.LinkProductRangeRepository;
import com.stock.control.dao.LinkStockistBrandRepository;
import com.stock.control.dao.ProductRepository;
import com.stock.control.dao.RangeRepository;
import com.stock.control.dao.StockistRepository;
import com.stock.control.model.Brand;
import com.stock.control.model.Category;
import com.stock.control.model.LinkBrandCategory;
import com.stock.control.model.LinkCategoryProduct;
import com.stock.control.model.LinkProductRange;
import com.stock.control.model.LinkStockistBrand;
import com.stock.control.model.Product;
import com.stock.control.model.Range;
import com.stock.control.model.Stockist;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, MvcConfiguration.class })
@WebAppConfiguration
public class TestLoadDatabase {

	@Autowired
	private StockistRepository stockistR;

	@Autowired
	private BrandRepository brandR;

	@Autowired
	private CategoryRepository categoryR;

	@Autowired
	private ProductRepository productR;

	@Autowired
	private RangeRepository rangeR;

	@Autowired
	private LinkStockistBrandRepository stockistBrandR;

	@Autowired
	private LinkBrandCategoryRepository brandCategoryR;

	@Autowired
	private LinkCategoryProductRepository categoryProductR;

	@Autowired
	private LinkProductRangeRepository productRangeR;

	@Test
	public void load() throws Exception {
		importFile();

	}

	public void importFile() {
		String csvFile = "E:\\Lavoro\\Gestione-magazzino\\Palla.csv";
		String line = "";
		String cvsSplitBy = ";";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				String[] record = line.split(cvsSplitBy);
				// stockist brand Category CODICE PRODOTTO descrizione COLORE
				// PREZZO

				Stockist s = stockistR.findByDescr(record[0]);
				if (s == null) {
					s = new Stockist();
					s.setDescr(record[0]);
					s = stockistR.save(s);
				}

				Brand b = brandR.findByDescr(record[1]);
				if (b == null) {
					b = new Brand();
					b.setDescr(record[1]);
					b = brandR.save(b);

					LinkStockistBrand l = new LinkStockistBrand();
					l.setStockist(s);
					l.setBrand(b);
					stockistBrandR.save(l);
				}

				Category c = categoryR.findByDescr(record[2]);
				if (c == null) {
					c = new Category();
					c.setDescr(record[2]);
					c = categoryR.save(c);

					LinkBrandCategory l = new LinkBrandCategory();
					l.setBrand(b);
					l.setCategory(c);
					brandCategoryR.save(l);
				}

				Product p = productR.findByCodProduct(record[3]);
				if (p == null) {
					p = new Product();
					p.setDescr(record[4]);
					p.setCodProduct(record[3]);
					p = productR.save(p);

					LinkCategoryProduct l = new LinkCategoryProduct();
					l.setProduct(p);
					l.setCategory(c);
					categoryProductR.save(l);
				}

				Range r = new Range();
				r.setDescr(record[5]);
				r.setSellingPrice(new BigDecimal(record[6].replace(",", ".")));
				r.setColor(record[5]);
				r = rangeR.save(r);

				LinkProductRange l = new LinkProductRange();
				l.setProduct(p);
				l.setRange(r);
				productRangeR.save(l);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
