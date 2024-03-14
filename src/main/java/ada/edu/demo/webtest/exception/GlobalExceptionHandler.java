package ada.edu.demo.webtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {


    // @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    // @ExceptionHandler(Exception.class)
    // public ModelAndView handle400Errors(Exception ex) {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("/errorpages/error_general");
    //     mv.addObject("exception", ex.getMessage());
    //     return mv;
    // }


    // @ExceptionHandler(StudentException.class)
    // public ModelAndView handle500Errors(Exception ex) {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("/errorpages/error_student");
    //     mv.addObject("exception", ex.getMessage());
    //     return mv;
    // }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(Exception.class)
    public ModelAndView handle400Errors(Exception ex) {
        ModelAndView mv = new ModelAndView("/errorpages/error_general");
        mv.addObject("exception", "Bad request");
        mv.setStatus(HttpStatus.METHOD_NOT_ALLOWED); // Set the status code
        return mv;
    }

    @ExceptionHandler(StudentException.class) // Assuming StudentException is your custom exception
    public ModelAndView handle500Errors(StudentException ex) {
        ModelAndView mv = new ModelAndView("/errorpages/error_student"); 
        // mv.addObject("exception", "Entity error (" + ex.errorNum() + ") : " + ex.getMessage()); 
        mv.addObject("exception", ex.getMessage()); // Use ex.getMessage() directly
        mv.setStatus(HttpStatus.valueOf(ex.errorNum())); // Set based on exception
        return mv;
    }

}
