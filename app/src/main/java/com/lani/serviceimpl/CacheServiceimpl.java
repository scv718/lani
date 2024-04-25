package com.lani.serviceimpl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.lani.service.CacheService;

@Service
public class CacheServiceimpl implements CacheService {

	@Override
	@CacheEvict(cacheNames = "main_data")
	public void refreshCache() {
		System.out.println("캐시 초기화");
	}



}
