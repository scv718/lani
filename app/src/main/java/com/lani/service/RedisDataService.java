package com.lani.service;

import java.io.IOException;
import java.util.List;

import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;

public interface RedisDataService {

	public List<ResponseMapData> sendMapData();
}
