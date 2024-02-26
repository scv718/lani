package com.lani.service;

import java.util.List;

import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;

public interface DataService {

	public void addMapData(RequestMapData mapData);
	public List<ResponseMapData> sendMapData();
}
