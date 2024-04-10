package co.com.carlos.scheduler.scheduler.service.impl;

import co.com.carlos.scheduler.scheduler.controller.SchedulerController;
import co.com.carlos.scheduler.scheduler.dto.Request;
import co.com.carlos.scheduler.scheduler.exception.InvalidCronException;
import co.com.carlos.scheduler.scheduler.exception.InvalidUrlException;
import co.com.carlos.scheduler.scheduler.service.SchedulerService;
import co.com.carlos.scheduler.scheduler.utils.HeaderScraper;
import co.com.carlos.scheduler.scheduler.utils.Pinger;
import co.com.carlos.scheduler.scheduler.utils.ScrapingJob;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Component
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger logger = LogManager.getLogger(SchedulerServiceImpl.class);

    private static final String JOB_IDENTITY = "scrapingJob";

    @Autowired
    private Scheduler scheduler;
    @Override
    public void createCron(final Request request) throws SchedulerException, IOException {
        final String url = request.getUrl();
        final String cronExpression = request.getCron();

        //Validate the url
        Pinger.ping(url);

        try{
            final CronTrigger trigger = newTrigger()
                    .withSchedule(cronSchedule(cronExpression))
                    .build();
            if(scheduler.checkExists(JobKey.jobKey(JOB_IDENTITY))){
                scheduler.deleteJob(JobKey.jobKey(JOB_IDENTITY));
            }
            scheduler.scheduleJob(executeJob(url),trigger);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InvalidCronException("Invalid cron expression: " + cronExpression);
        }
    }

    private JobDetail executeJob(String url) throws IOException {
        logger.info("Creating scheduled job");
        // Implement job creation logic with URL argument
        final JobDetail jobDetail = JobBuilder.newJob(ScrapingJob.class)
                .withIdentity(JOB_IDENTITY)
                .usingJobData("url", url)
                .usingJobData("scrapingInstructions", "Scrape the first 1000 characters")
                .build();
        return jobDetail;
    }
}
