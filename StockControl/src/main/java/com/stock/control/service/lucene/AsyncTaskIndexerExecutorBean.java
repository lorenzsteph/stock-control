package com.stock.control.service.lucene;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskIndexerExecutorBean implements AsyncTaskIndexerExecutor {

	private static final Logger log = LoggerFactory.getLogger(AsyncTaskIndexerExecutorBean.class);

	private static final long RUN_TASK_EVERY_20_MIN = 1200000l;
	private static final long DELAY_RUN_TASK_SEC = 15000l;

	@Autowired
	private SuperSearchIndexer superSearchIndexer;

	@Scheduled(fixedRate = RUN_TASK_EVERY_20_MIN, initialDelay = DELAY_RUN_TASK_SEC)
	public void runTaskAsync() throws Exception {
		log.info(">>>>> ScheduledTaskIndexer START : " + new Date());
		superSearchIndexer.indexer();
		log.info(">>>>> ScheduledTaskIndexer END : " + new Date());
	}
}
