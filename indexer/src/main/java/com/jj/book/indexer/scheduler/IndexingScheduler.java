package com.jj.book.indexer.scheduler;

import com.jj.book.indexer.job.IndexingJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class IndexingScheduler {
    private final SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void start() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail job = JobBuilder.newJob(IndexingJob.class).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?")).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
