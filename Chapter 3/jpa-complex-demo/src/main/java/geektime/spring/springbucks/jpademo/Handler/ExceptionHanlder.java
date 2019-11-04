package geektime.spring.springbucks.jpademo.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 11:13
 * @Description:
 */
@RestControllerAdvice
public class ExceptionHanlder {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            for (ConstraintViolation<?> item : constraintViolations) {
                System.out.println(item.getMessage());
            }
        } else {
            System.out.println(exception.getLocalizedMessage());
        }
        return exception.getMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalState(IllegalStateException exception) {
        System.out.println(exception.getMessage());
        return exception.getMessage();
    }

}
