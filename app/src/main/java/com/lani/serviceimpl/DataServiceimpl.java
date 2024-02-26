package com.lani.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lani.dao.MapDataDao;
import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;
import com.lani.service.DataService;

@Service
public class DataServiceimpl implements DataService{


	@Autowired(required = true)
	MapDataDao dataDao;


	@Override
	public List<ResponseMapData> sendMapData() {


		return dataDao.selectListData();
	}

	@Override
	public void addMapData(RequestMapData mapData) {

		dataDao.insertMapData(mapData);
	}



}
