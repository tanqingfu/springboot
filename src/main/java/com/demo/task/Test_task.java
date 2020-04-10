package com.demo.task;

import com.demo.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Test_task {

	//@Component 注册为spring组件

	/*
	1、fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，
		并间隔给定的时间后，执行下一次。

	2、fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，
		但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。

	3、cron表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。

	4、initialDelay 。如： @Scheduled(initialDelay = 10000,fixedRate = 15000,这个定时器就是在上一个的基础上加了一个
		initialDelay = 10000 意思就是在容器启动后,延迟10秒后再执行一次定时器,以后每15秒再执行一次该定时器。
	*/


	@Scheduled(cron="0 */1 * * * ?")
	public void Test_task(){
		//System.out.println("定时1分钟执行一次");
	}

	//*/5 * * * * ?  5秒执行一次
	@Scheduled(cron = "*/5 * * * * ?")
	public  void time(){
		//System.out.println("每5秒执行一次："+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}

	//fixedRate 定义一个按一定频率执行的定时任务
	@Scheduled(fixedRate = 5000)
	public  void fixedRate(){
		System.out.println("每5毫秒执行fixedRate一次："+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}

	//fixedDelay 定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间。
	@Scheduled(fixedDelay = 5000)
	public  void fixedDelay(){
		System.out.println("每5毫秒执行fixedDelay一次："+ DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}

	//@Scheduled(initialDelay = 10000,fixedRate = 15000,这个定时器就是在上一个的基础上加了一个
	// initialDelay = 10000 意思就是在容器启动后,延迟10秒后再执行一次定时器,以后每15秒再执行一次该定时器
	@Scheduled(initialDelay = 1000,fixedRate = 5000)
	public void initialDelay(){
		System.out.println("华丽的分割符----"+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}




}
