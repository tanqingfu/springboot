package com.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test_task {
	//@Component 注册为spring组件
	@Scheduled(cron="0 */1 * * * ?")
	public void Test_task(){

		System.out.println("定时1分钟执行一次");
	}

}
