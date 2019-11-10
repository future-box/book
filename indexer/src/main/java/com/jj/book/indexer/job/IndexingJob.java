package com.jj.book.indexer.job;

import com.jj.book.indexer.model.ISBNRequest;
import com.jj.book.indexer.service.CheckDocService;
import com.jj.book.indexer.service.IndexingService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class IndexingJob implements Job {
    @Autowired
    private IndexingService indexingService;
    @Autowired
    private CheckDocService checkDocService;

    @Override
    public void execute(JobExecutionContext context) {
        for (int year = 2000; year <= 2019; year++) {
            for (int month = 1; month <= 12; month++) {
                LocalDate date = LocalDate.of(year, month, 1);
                String startDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
                String endDate = date.plusDays(31L).format(DateTimeFormatter.BASIC_ISO_DATE);
                if (checkDocService.isDocByDateExist(startDate, endDate)) {
                    ISBNRequest isbnRequest = new ISBNRequest();
                    isbnRequest.setStartPublishDate(startDate);
                    isbnRequest.setEndPublishDate(endDate);
                    indexingService.index(isbnRequest);
                    log.info("job executed. isbnRequest: [{}]", isbnRequest.toString());
                }
            }
        }
    }
}