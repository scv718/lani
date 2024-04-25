package com.lani.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lani.dao.MapDataDao;
import com.lani.event.DatabaseInsertEvent;
import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;
import com.lani.service.DataService;

@Service
public class DataServiceimpl implements DataService {

	@Autowired
	MapDataDao dataDao;

	@Autowired
	private RedisService redisService;

	private static List<ResponseMapData> sendList;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	public DataServiceimpl(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}


	private List<ResponseMapData> selectList() {
		List<ResponseMapData> listData = dataDao.selectListData();
		for (ResponseMapData data : listData) {
			String path = data.getDate() + "_" + data.getStore() + ".jpg";
			data.setFilePath(path);
		}
		return listData;
	}

	@Override
	public List<ResponseMapData> sendMapData() {

		boolean sizeCheck = true;
		boolean sendListCheck = (sendList == null || sendList.isEmpty()) ? true : false;

		if (sendListCheck) {
			sendList.addAll(selectList());
			return sendList;
		}

		int cnt = dataDao.selectCountData();
		sizeCheck = (sendList.size() == cnt) ? true : false;
		if (!sizeCheck) {
			sendList.addAll(selectList());
		}
		return sendList;
	}
	// ----

	@Transactional
	@Override
	public void addMapData(RequestMapData mapData) throws IllegalStateException, IOException {

		dataDao.insertMapData(mapData);

		redisService.convertAndSend("insert", "insert data");
		eventPublisher.publishEvent(new DatabaseInsertEvent(this));
	}

}
