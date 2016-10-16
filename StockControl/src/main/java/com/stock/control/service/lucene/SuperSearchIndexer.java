package com.stock.control.service.lucene;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.control.configuration.properties.StockProperties;

@Service
public class SuperSearchIndexer {

	private static final Logger log = LoggerFactory.getLogger(SuperSearchIndexer.class);

	@Autowired
	private StockProperties stockProperties;

	@Autowired
	private DataSource dataSourceStock;

	public void indexer() throws Exception {
		File indexDir = new File(stockProperties.getPathLucene());
		SuperSearchIndexer indexer = new SuperSearchIndexer();
		try {
			StandardAnalyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
			indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexDir.toPath()), indexWriterConfig);
			log.info("Indexing to directory '" + indexDir + "'...");

			indexer.indexGenerico(indexWriter, dataSourceStock.getConnection(), "storehouse",
					"SELECT stockist, brand, category, (cod_product || ' ' || product) as product, range, selling_price, price_order, store_total, store_price, id_product_for_order FROM storehouse");
			indexer.indexGenerico(indexWriter, dataSourceStock.getConnection(), "stockist", "SELECT id_stockist, descr FROM stockist");
			indexer.indexGenerico(indexWriter, dataSourceStock.getConnection(), "product", "SELECT cod_product, descr, range, selling_price, id_brand, id_category FROM product");

			indexWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int indexGenerico(IndexWriter writer, Connection conn, String TBNAME, String sql) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		log.debug("Indexing " + TBNAME);

		int i = 0;
		int step = 1;
		String colonna;
		String valore;
		while (rs.next()) {
			Document d = new Document();
			d.add(new TextField("TBNAME", TBNAME, Field.Store.YES));
			ResultSetMetaData columns = rs.getMetaData();
			int ii = 0;
			while (ii < columns.getColumnCount()) {
				ii++;
				colonna = columns.getColumnName(ii);
				valore = rs.getString(colonna);
				d.add(new TextField(colonna, valore == null ? "" : valore, Field.Store.YES));

			}

			writer.addDocument(d);
			i++;
			step++;
			if (step > 1000) {
				log.debug("Indexed " + i + " records...");
				step = 1;
			}
		}
		log.debug(i + " records have been indexed successfully");
		return i;
	}

}
