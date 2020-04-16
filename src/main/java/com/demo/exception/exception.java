package com.demo.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wqx on 2019/3/20.
 */
//第五种方法：使用Configuration注解并且实现HandlerExceptionResolver接口
@Configuration
public class exception implements HandlerExceptionResolver{

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, @Nullable Object o, Exception e) {
        ModelAndView mv=new ModelAndView();
        //判断是哪种错误
        if(e instanceof ArithmeticException){
            mv.setViewName("error1");
        }
        if(e instanceof NullPointerException){
            mv.setViewName("error2");
        }
        mv.addObject("error",e.toString());
        return mv;
    }
}

