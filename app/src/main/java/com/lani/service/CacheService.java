package com.lani.service;

public interface CacheService {

	public void refreshCache();

	void evictCache(String cacheName);

}
