package com.lani.event;

import java.time.Duration;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.lani.common.KeyEnum;
import com.lani.service.CacheService;
import com.lani.serviceimpl.RedisService;
import com.lani.util.BeanUtils;

@Component
public class DatabaseInsertEventListener implements ApplicationListener<DatabaseInsertEvent> {

    private final RedisService redisService;

    public DatabaseInsertEventListener(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void onApplicationEvent(DatabaseInsertEvent event) {
        // INSERT 이벤트 발생 시 캐시 갱신 작업 수행

    	CacheService cacheService = (CacheService) BeanUtils.getBean(CacheService.class);
    	cacheService.refreshCache();

    	System.err.println("인서트 발생 캐시 작업 수행");
    	redisService.deleteValues(KeyEnum.MAP_DATA_KEY.value());
        redisService.setList(KeyEnum.MAP_DATA_KEY.value(), event.fetchNewData(), Duration.ofMinutes(30));

    }
}