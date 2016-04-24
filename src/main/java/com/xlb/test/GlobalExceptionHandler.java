package com.xlb.test;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理拦截
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ServiceException handleException(RuntimeException e, HttpServletResponse response) {
        response.setStatus(404);
        ServiceException se = new ServiceException(e.getMessage());
        return se;
    }

}
