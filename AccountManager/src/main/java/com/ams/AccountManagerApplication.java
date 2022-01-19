package com.ams;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling //Quartz 사용하기 위한 어노테이션
public class AccountManagerApplication {

	@Scheduled(cron = "* * * * * *")
	public void testQuartz() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AccountManagerApplication.class, args);
	}

}
