package co.com.carlos.scheduler.scheduler.controller;

import co.com.carlos.scheduler.scheduler.dto.Request;
import co.com.carlos.scheduler.scheduler.dto.Response;
import co.com.carlos.scheduler.scheduler.service.SchedulerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/scheduler")
@CrossOrigin(origins = "http://localhost:4200/", methods = RequestMethod.POST) // Replace with your frontend origin and method
public class SchedulerController {
    private static final Logger logger = LogManager.getLogger(SchedulerController.class);

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<Response> createPost(@RequestBody Request request) throws SchedulerException, IOException {
        // Create a new post and return it
        logger.info("Starting to create scheduled job with parameters: " +
                " \nurl -> " + request.getUrl() +
                "\ncron expression -> " + request.getCron());
        return new ResponseEntity<>(schedulerService.createCron(request), HttpStatus.OK);

    }

}
