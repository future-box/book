package com.jj.book.indexer.job;

import com.jj.book.indexer.config.SearchProperty;
import com.jj.book.indexer.service.IndexingService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndexingJob implements Job {
    private final IndexingService indexingService;
    private final SearchProperty searchProperty;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        new IndexRequest()
        .index();
    }

}