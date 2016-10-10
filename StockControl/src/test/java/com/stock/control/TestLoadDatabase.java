package com.stock.control;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.configuration.JPAConfiguration;
import com.stock.control.configuration.MvcConfiguration;
import com.stock.control.dao.BrandRepository;
import com.stock.control.dao.CategoryRepository;
import com.stock.control.dao.ProductRepository;
import com.stock.control.model.Brand;
import com.stock.control.model.Category;
import com.stock.control.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, MvcConfiguration.class })
@WebAppConfiguration
public class TestLoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(TestLoadDatabase.class);

	@Autowired
	private BrandRepository brandR;

	@Autowired
	private CategoryRepository categoryR;

	@Autowired
	private ProductRepository productR;

	@Test
	public void loadXls() throws Exception {

		List<Map<String, Object>> products = loadExcel("E:\\Lavoro\\Gestione-magazzino\\Inventario_Settembre_2016.xlsx");
		save(products);
	}

	private Map<String, Integer> loadConfiguration(int sheet) throws Exception {

		Map<String, Integer> configuration = new HashMap<String, Integer>();

		if (sheet == 0 || sheet == 1 || sheet == 3 || sheet == 4 || sheet == 5) {
			configuration.put("BRAND", 0);
			configuration.put("DESCR_PRODUCT_1", 1);
			configuration.put("DESCR_PRODUCT_2", 2);
			configuration.put("RANGE_PRODUCT", 3);
			configuration.put("COD_PRODUCT", 4);
			configuration.put("PRICE_PRODUCT", 6);
		} else if (sheet == 2) {
			configuration.put("BRAND", 0);
			configuration.put("DESCR_PRODUCT_1", 1);
			configuration.put("RANGE_PRODUCT", 2);
			configuration.put("COD_PRODUCT", 3);
			configuration.put("PRICE_PRODUCT", 5);
		} else if (sheet == 6) {
			configuration.put("BRAND", 0);
			configuration.put("DESCR_PRODUCT_1", 2);
			configuration.put("RANGE_PRODUCT", 3);
			configuration.put("COD_PRODUCT", 4);
			configuration.put("PRICE_PRODUCT", 6);
		} else {
			log.error("no configuration layout!");
			throw new Exception("no configuration layout!");
		}

		return configuration;
	}

	private List<Map<String, Object>> loadExcel(String fileName) {
		List<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			for (ExcelEnum excelEnum : ExcelEnum.values()) {
				log.info(">>>>>>> sheet : " + excelEnum.getName());
				XSSFSheet sheet = workbook.getSheetAt(excelEnum.getSheet());

				Map<String, Integer> configuration = loadConfiguration(excelEnum.getSheet());

				Category category = findCategory(sheet);
				Map<String, Object> product = null;

				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					product = new HashMap<String, Object>();

					Row row = rowIterator.next();
					if (valueToSkip(row)) {
						continue;
					}
					log.debug("ROW num -> " + row.getRowNum() + " of sheet : " + excelEnum.getName());

					Brand b = findBrandByDescr(row.getCell(configuration.get("BRAND")).getStringCellValue());
					product.put("CATEGORY", category);

					product.put("CATEGORY", category);
					product.put("BRAND", b);

					String descr2 = "";
					if (configuration.get("DESCR_PRODUCT_2") != null) {
						descr2 = row.getCell(configuration.get("DESCR_PRODUCT_2")).getStringCellValue();
					}

					product.put("DESCR_PRODUCT", row.getCell(configuration.get("DESCR_PRODUCT_1")).getStringCellValue() + " " + descr2);
					product.put("RANGE_PRODUCT", row.getCell(configuration.get("RANGE_PRODUCT")).getStringCellValue());
					product.put("COD_PRODUCT", row.getCell(configuration.get("COD_PRODUCT")).getStringCellValue());
					product.put("PRICE_PRODUCT", row.getCell(configuration.get("PRICE_PRODUCT")).getNumericCellValue());

					products.add(product);
					log.debug("add product");
				}
			}

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	private boolean valueToSkip(Row row) {
		boolean skip = false;

		if (row.getCell(0) == null || "MARCA".equals(row.getCell(0).getStringCellValue()) || "TOTALE".equals(row.getCell(0).getStringCellValue()) || "Gara".equals(row.getCell(0).getStringCellValue())
				|| "Gaara".equals(row.getCell(0).getStringCellValue())) {
			skip = true;
		}

		return skip;
	}

	private void save(List<Map<String, Object>> records) {

		for (Map<String, Object> record : records) {

			Product p = productR.findByCodProduct((String) record.get("COD_PRODUCT"));
			if (p == null) {
				p = new Product();
				p.setDescr((String) record.get("DESCR_PRODUCT"));
				p.setCodProduct((String) record.get("COD_PRODUCT"));
				p.setSellingPrice(new BigDecimal((Double) record.get("PRICE_PRODUCT")));
				p.setRange((String) record.get("RANGE_PRODUCT"));
				p.setCategory((Category) record.get("CATEGORY"));
				p.setBrand((Brand) record.get("BRAND"));
				p = productR.save(p);
				log.debug("Product save -> " + p.toString());
			}
		}
	}

	private Brand findBrandByDescr(String descr) {
		Brand b = brandR.findByDescr(descr);
		if (b == null) {
			log.debug("brand not found!!! " + descr);
		}
		return b;
	}

	private Category findCategory(XSSFSheet sheet) {
		Category c = categoryR.findByDescr(sheet.getSheetName());
		if (c == null) {
			c = new Category();
			c.setDescr(sheet.getSheetName());
			c = categoryR.save(c);
		}

		return c;
	}

}
