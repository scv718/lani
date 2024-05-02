package com.lani.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.lani.service.CacheService;

@Service
public class CacheServiceimpl implements CacheService {



    @Autowired
    private CacheManager cacheManager;


    @Override
    public void evictCache(String cacheName) {
        cacheManager.getCache(cacheName).clear();
        System.out.println(cacheName + " 캐시 초기화");
    }


	@Override
	@CacheEvict(cacheNames = "main_data")
	public void refreshCache() {
		System.out.println("캐시 초기화");
	}




}
