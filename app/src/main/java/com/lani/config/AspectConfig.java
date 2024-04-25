package com.lani.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.lani.serviceimpl.RedisDataServiceimpl;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AspectConfig {


	@Pointcut("execution(* com.lani.serviceimpl.RedisDataServiceimpl.sendMapData(..))")
	private void sendMapDataPointcut() {

	}

//    @Before("sendMapDataPointcut()")
//    public void beforeSendMapData() {
//        RedisDataServiceimpl proxy = (RedisDataServiceimpl) AopContext.currentProxy();
//        proxy.sendMapData();
//    }


}
