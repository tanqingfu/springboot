package com.demo.test;

import com.demo.util.DateUtils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class time {
    public static void main(String[] args) {
        timer();
        ScheduledExecutorService();
    }

    public static void  timer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //System.out.println("task  run:"+ new Date());
                System.out.println("执行时间为:"+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
            }
        };
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);
    }

    public static void ScheduledExecutorService(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间 3、任务执行间隔 4、间隔时间单位
        service.scheduleAtFixedRate(()->System.out.println("执行时间为:"+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")), 0, 3, TimeUnit.SECONDS);
    }
}
