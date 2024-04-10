package co.com.carlos.scheduler.scheduler.utils;

import co.com.carlos.scheduler.scheduler.controller.SchedulerController;
import co.com.carlos.scheduler.scheduler.exception.InvalidCronException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScrapingJob implements Job {

    private static final Logger logger = LogManager.getLogger(ScrapingJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        final String url = (String) dataMap.get("url");

        try {
            final String pingResult = Pinger.ping(url);
            final String scrapedHeaders = HeaderScraper.scrapeHeaders(url);

            logger.info("ping result: " + pingResult);
            logger.info("headers result: " + scrapedHeaders);
        } catch (Exception e) {
            throw new InvalidCronException(e.getMessage());
        }

        //Show the information about the job
    }
}
