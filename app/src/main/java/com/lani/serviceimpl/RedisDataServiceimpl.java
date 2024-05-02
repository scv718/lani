package com.lani.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lani.dao.MapDataDao;
import com.lani.response.ResponseMapData;
import com.lani.service.RedisDataService;

@Service
public class RedisDataServiceimpl implements RedisDataService {

	@Autowired
	private MapDataDao dataDao;

	@Autowired
	private RedisService redisService;

//    @Autowired
//    private DataServiceimpl dataService;


	@Cacheable(cacheNames = "main_data", key = "#root.method.name")
	public List<ResponseMapData> sendMapData() {
	    List<ResponseMapData> data = dataDao.selectListData();
	    if (data == null || data.isEmpty()) {
	        return new ArrayList<>();
	    }
	    return data;
	}

	// redisService 로 직접적인 set 없이 바로 어노테이션으로 캐시 등록
	@CachePut(cacheNames = "main_data", key = "#root.method.name")
	public List<ResponseMapData> updateMapData() {
	    List<ResponseMapData> updatedData = dataDao.selectListData();
	    return updatedData;
	}



	// 기존 잔재 구형방식
//    @Cacheable(cacheNames = "main_data")
//	public List<ResponseMapData> sendMapData() {
//		redisService.convertAndSend("select", "select main");
//
//		List<ResponseMapData> cachedData = redisService.getList(KeyEnum.MAP_DATA_KEY.value());
//
//		if (cachedData == null || cachedData.isEmpty()) {
//			List<ResponseMapData> listData = dataDao.selectListData();
//			redisService.setList(KeyEnum.MAP_DATA_KEY.value(), listData, Duration.ofMinutes(30)); // Example duration
//			return listData;
//		}
//
//        return cachedData;
//	}
//
//



	// getValues 잔재
	private List<ResponseMapData> parseCachedData(String cachedData) {
		String[] items = cachedData.split("\\), ");
		List<ResponseMapData> dataList = new ArrayList<>();
		for (String item : items) {
			String[] parts = item.split(", ");

			String date = extractValue(parts[0]);
			String title = extractValue(parts[1]);
			String store = extractValue(parts[2]);
			String point_x = extractValue(parts[3]);
			String point_y = extractValue(parts[4]);
			String filePath = extractValue(parts[5]);
			String main_img = extractValue(parts[6]).equals("true)]") ? extractValue(parts[6]).split("\\)]")[0]
					: extractValue(parts[6]);

			ResponseMapData data = new ResponseMapData(date, title, store, point_x, point_y, filePath, main_img);
			dataList.add(data);
		}

		return dataList;
	}

	private String extractValue(String pair) {
		return pair.split("=")[1];
	}

}
