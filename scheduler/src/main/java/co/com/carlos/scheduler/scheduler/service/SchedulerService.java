package co.com.carlos.scheduler.scheduler.service;

import co.com.carlos.scheduler.scheduler.dto.Request;
import org.quartz.SchedulerException;

import java.io.IOException;

public interface SchedulerService {
    void createCron(final Request request) throws SchedulerException, IOException;
}
