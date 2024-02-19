package com.lani.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lani.dao.MapDataDao;
import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;
import com.lani.service.DataService;

@Service
public class DataServiceimpl implements DataService{


	@Autowired
	MapDataDao dataDao;


	@Override
	public ResponseMapData sendMapData() {

		return null;
	}

	@Override
	public void addMapData(RequestMapData mapData) {



	}



}
