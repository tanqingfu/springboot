package com.demo.task;

import com.demo.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Test_task {
	//@Component 注册为spring组件
    //*/5 * * * * ?  5秒执行一次
	@Scheduled(cron="0 */1 * * * ?")
	public void Test_task(){
		System.out.println("定时1分钟执行一次");
	}

	@Scheduled(cron = "*/5 * * * * ?")
	public  void time(){
		System.out.println("每5秒执行一次："+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}

}
