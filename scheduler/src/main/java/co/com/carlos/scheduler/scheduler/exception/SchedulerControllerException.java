package co.com.carlos.scheduler.scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchedulerControllerException {

    @ExceptionHandler(value = InvalidUrlException.class)
    public ResponseEntity<Object> exception(InvalidUrlException exception) {
        return new ResponseEntity<>("The url expression is invalid. Please double check the parameters.", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = InvalidCronException.class)
    public ResponseEntity<Object> exception(InvalidCronException exception) {
        return new ResponseEntity<>("The cron expression is invalid. Please double check the parameters.\n" +
                "Try using the cron expressions based on this link: " +
                "https://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html", HttpStatus.NOT_ACCEPTABLE);
    }
}
