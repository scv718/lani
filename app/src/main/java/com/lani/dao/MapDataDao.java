package com.lani.dao;


import java.util.List;

import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;

public interface MapDataDao {

	/*
	 * 데이트 추가
	 */
	public void insertMapData(RequestMapData data);


	//////////////////


	/*
	 * 전체 뿌려주는 쿼리
	 */
	public List<ResponseMapData> selectListData();
}
