package co.com.carlos.scheduler.scheduler.service;

import co.com.carlos.scheduler.scheduler.dto.Request;
import co.com.carlos.scheduler.scheduler.dto.Response;
import org.quartz.SchedulerException;

import java.io.IOException;

public interface SchedulerService {
    Response createCron(Request request) throws SchedulerException, IOException;
}
