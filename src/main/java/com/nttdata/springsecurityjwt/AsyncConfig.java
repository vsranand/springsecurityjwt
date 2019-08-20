package com.nttdata.springsecurityjwt;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import com.nttdata.springsecurityjwt.exception.CustomAsyncExceptionHandler;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
	    return new CustomAsyncExceptionHandler();
	}
}
