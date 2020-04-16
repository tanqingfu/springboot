package com.demo.filter;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常处理
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onValidFail(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return "无效的请求参数";
        //return new ResponseEntity(HttpResponse.fail("无效的请求参数").stringfy(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String onMethodNotFound(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return "无效的请求方法";
        //return new ResponseEntity(HttpResponse.fail("无效的请求方法").stringfy(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String onHandlerNotFound(Exception e, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(404);
        return "无效的请求路径";
        //return new ResponseEntity(HttpResponse.fail("无效的请求路径").stringfy(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String onServerError(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return "服务器发生异常";
        //return new ResponseEntity(HttpResponse.fail("服务器发生异常").detail(e.getMessage()).stringfy(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public String onUnknownException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        return "未知异常";
        //return new ResponseEntity(HttpResponse.fail("未知异常").detail(e.getMessage()).stringfy(), HttpStatus.OK);
    }
}


