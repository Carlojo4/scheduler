package co.com.carlos.scheduler.scheduler.service.impl;

import co.com.carlos.scheduler.scheduler.dto.Request;
import co.com.carlos.scheduler.scheduler.dto.Response;
import co.com.carlos.scheduler.scheduler.service.SchedulerService;
import co.com.carlos.scheduler.scheduler.utils.HeaderScraper;
import co.com.carlos.scheduler.scheduler.utils.Pinger;
import co.com.carlos.scheduler.scheduler.utils.ScrapingJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private Scheduler scheduler;
    @Override
    public Response createCron(Request request) throws SchedulerException, IOException {
        String url = request.getUrl();
        String cronExpression = request.getCron();

        CronTrigger trigger = newTrigger()
                .withSchedule(cronSchedule(cronExpression))
                .build();

        scheduler.scheduleJob(executeJob(url),trigger);

        return null;
    }

    private JobDetail executeJob(String url) throws IOException {

        // Implement job creation logic with URL argument
        JobDetail jobDetail = JobBuilder.newJob(ScrapingJob.class)
                .withIdentity("myScrapingJob")
                .usingJobData("url", url)
                .usingJobData("scrapingInstructions", "Scrape the first 1000 characters")
                .build();
        return jobDetail;
    }
}
