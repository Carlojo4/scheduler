package co.com.carlos.scheduler.scheduler.controller;

import co.com.carlos.scheduler.scheduler.dto.Request;
import co.com.carlos.scheduler.scheduler.exception.InvalidCronException;
import co.com.carlos.scheduler.scheduler.exception.InvalidUrlException;
import co.com.carlos.scheduler.scheduler.service.SchedulerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.rmi.server.ExportException;

@RestController
@RequestMapping("/api/scheduler")
@CrossOrigin(origins = "http://localhost:4200/", methods = RequestMethod.POST) // Replace with your frontend origin and method
public class SchedulerController {
    private static final Logger logger = LogManager.getLogger(SchedulerController.class);

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Request request) throws SchedulerException, IOException {
        try{
            logger.info("Starting to create scheduled job with parameters: " +
                    " \nurl -> " + request.getUrl() +
                    "\ncron expression -> " + request.getCron());
            schedulerService.createCron(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidUrlException e) {
            throw new InvalidUrlException(e.getMessage());
        } catch (InvalidCronException e) {
            throw new InvalidCronException(e.getMessage());
        }
    }

}
