package com.lani.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.lani.service.CacheService;
import com.lani.serviceimpl.RedisService;

@Component
public class DatabaseInsertEventListener implements ApplicationListener<DatabaseInsertEvent> {

//    private final RedisService redisService;
    private final CacheService cacheService;

    public DatabaseInsertEventListener(CacheService cacheService) {
//    	public DatabaseInsertEventListener(RedisService redisService, CacheService cacheService) {
//        this.redisService = redisService;
        this.cacheService = cacheService;
    }


    @Override
    public void onApplicationEvent(DatabaseInsertEvent event) {
    	 if ("date_data".equals(event.getTableName())) {
             cacheService.evictCache("main_data");
             System.err.println("관련 캐시 삭제 수행");
         }
    }

//    @Override
//    public void onApplicationEvent(DatabaseInsertEvent event) {
//        // INSERT 이벤트 발생 시 캐시 갱신 작업 수행
//
//    	CacheService cacheService = (CacheService) BeanUtils.getBean(CacheService.class);
//    	cacheService.refreshCache();
//
//    	System.err.println("인서트 발생 캐시 작업 수행");
//    	redisService.deleteValues(KeyEnum.MAP_DATA_KEY.value());
//        redisService.setList(KeyEnum.MAP_DATA_KEY.value(), event.fetchNewData(), Duration.ofMinutes(30));
//
//    }
}