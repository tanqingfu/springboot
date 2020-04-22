package com.demo.filter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Listener监听器
 */
@WebListener
public class onLineCount implements HttpSessionListener {
    public int count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
        se.getSession().setAttribute("count",count);
        System.out.println("线上有几个人："+se.getSession().getAttribute("count"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
        se.getSession().setAttribute("count",count);
        System.out.println("线上有几个人："+se.getSession().getAttribute("count"));

    }
}
