package com.lani.event;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEvent;

import com.lani.dao.MapDataDao;
import com.lani.response.ResponseMapData;
import com.lani.service.CacheService;
import com.lani.util.BeanUtils;

public class DatabaseInsertEvent extends ApplicationEvent {

    public DatabaseInsertEvent(Object source) {
        super(source);
    }

    // 새로운 데이터를 조회하는 메서드
    public List<ResponseMapData> fetchNewData() {

    	System.out.println("인서트 발생 데이터 조회");
    	MapDataDao dataDao = (MapDataDao) BeanUtils.getBean(MapDataDao.class);
    	List<ResponseMapData> listData = dataDao.selectListData();
        return listData;
    }
}