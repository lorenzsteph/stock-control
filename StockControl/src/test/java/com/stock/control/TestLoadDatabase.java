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
import com.stock.control.dao.ProductRepository;
import com.stock.control.dao.StockistRepository;
import com.stock.control.model.Brand;
import com.stock.control.model.Category;
import com.stock.control.model.Product;
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
					b.setStockist(s);
					b = brandR.save(b);
				}

				Category c = categoryR.findByDescr(record[2]);
				if (c == null) {
					c = new Category();
					c.setDescr(record[2]);
					c.setBrand(b);
					c = categoryR.save(c);
				}

				Product p = productR.findByCodProduct(record[3]);
				if (p == null) {
					p = new Product();
					p.setDescr(record[4]);
					p.setCodProduct(record[3]);
					p.setSellingPrice(new BigDecimal(record[6].replace(",", ".")));
					p.setRange(record[5]);
					p.setCategory(c);
					p = productR.save(p);

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
