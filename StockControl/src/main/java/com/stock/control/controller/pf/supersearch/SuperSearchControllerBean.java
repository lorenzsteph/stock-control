package com.stock.control.controller.pf.supersearch;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.FieldInfos;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.configuration.properties.StockProperties;
import com.stock.control.service.lucene.SuperSearchIndexer;
import com.stock.control.utils.ConstantStock;

@Component(value = "superSearchCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SuperSearchControllerBean implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(SuperSearchControllerBean.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private StockProperties stockProperties;

	@Autowired
	private SuperSearchIndexer superSearchIndexer;

	private String word;
	private int numberOfWord;
	private List<Map<String, String>> dataModel;
	private List<String> columnKeys;

	private List<ColumnModel> columns;

	public void search() {
		try {
			columnKeys = new ArrayList<String>();
			searchIndex(word);
			createDynamicColumns();
		} catch (Exception e) {
			log.error("Errore inatteso", e);
			// TODO messaggio errore x primefaces
		}
	}

	public void indexer() {
		log.info(">>>>> ScheduledTaskIndexer START : " + new Date());
		try {
			superSearchIndexer.indexer();
		} catch (Exception e) {
			log.error("Errore inatteso", e);
			// TODO messaggio errore x primefaces
		}
		log.info(">>>>> ScheduledTaskIndexer END : " + new Date());
	}

	private void createDynamicColumns() {

		columns = new ArrayList<ColumnModel>();

		for (String columnKey : columnKeys) {
			columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
		}
	}

	private void searchIndex(String queryStr) throws Exception {

		log.info(">>> Start " + new Date());

		File indexDir = new File(stockProperties.getPathLucene());

		IndexReader reader = DirectoryReader.open(FSDirectory.open(indexDir.toPath()));

		// Estrazione dall'indice di tutti i campi
		List<String> listaCampi = new ArrayList<String>();

		for (LeafReaderContext lrc : reader.leaves()) {
			LeafReader lr = lrc.reader();
			FieldInfos fis = lr.getFieldInfos();
			for (FieldInfo fi : fis) {
				listaCampi.add(fi.name);
			}
		}

		String[] stockArr = new String[listaCampi.size()];
		stockArr = listaCampi.toArray(stockArr);

		IndexSearcher searcher = new IndexSearcher(reader);

		MultiFieldQueryParser queryParser = new MultiFieldQueryParser(stockArr, new StandardAnalyzer());

		queryParser.setPhraseSlop(0);
		queryParser.setLowercaseExpandedTerms(true);

		Query query = queryParser.parse(queryStr);
		TopDocs topDocs = searcher.search(query, ConstantStock.MAX_HITS);
		ScoreDoc[] hits = topDocs.scoreDocs;

		// TODO: mettere in un altro metodo
		log.info(">>>>>>>>>> " + new Date());

		numberOfWord = hits.length;
		int numberMaxOfcolumns = 0;
		dataModel = new ArrayList<Map<String, String>>();
		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			// Explanation expl = searcher.explain(query, docId);

			List<IndexableField> listaFields = d.getFields();
			Iterator<IndexableField> ite = listaFields.iterator();

			Map<String, String> record = new HashMap<String, String>();
			int numberOfColumn = 0;
			while (ite.hasNext()) {

				IndexableField campo = ite.next();
				String campoHL = getHighlightedField(query, new StandardAnalyzer(), "", campo.stringValue());
				StringBuffer sb = new StringBuffer();
				sb.append("<div class=\"searchKey\">" + campo.name() + "</div>");
				sb.append("<div class=\"searchValue\">" + campoHL + "</div>");

				record.put(String.valueOf(numberOfColumn), sb.toString());
				if (numberOfColumn > numberMaxOfcolumns) {
					numberMaxOfcolumns = numberOfColumn;
					columnKeys.add(String.valueOf(numberOfColumn));
				}
				numberOfColumn++;

			}

			dataModel.add(record);

		}

		log.info(">>> End " + new Date());
	}

	private String getHighlightedField(Query query, Analyzer analyzer, String fieldName, String fieldValue) throws IOException, InvalidTokenOffsetsException {
		Formatter formatter = new SimpleHTMLFormatter("<span  class=\"search-word\">", "</span>");
		QueryScorer queryScorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, queryScorer);
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(queryScorer, Integer.MAX_VALUE));
		highlighter.setMaxDocCharsToAnalyze(Integer.MAX_VALUE);
		String hl = highlighter.getBestFragment(analyzer, fieldName, fieldValue);
		if (hl == null) {
			hl = fieldValue;
		}
		return hl;
	}

	static public class ColumnModel implements Serializable {

		private static final long serialVersionUID = 2006644615122053044L;

		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getNumberOfWord() {
		return numberOfWord;
	}

	public void setNumberOfWord(int numberOfWord) {
		this.numberOfWord = numberOfWord;
	}

	public List<Map<String, String>> getDataModel() {
		return dataModel;
	}

	public void setDataModel(List<Map<String, String>> dataModel) {
		this.dataModel = dataModel;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

}