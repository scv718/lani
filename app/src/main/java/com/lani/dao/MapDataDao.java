package com.lani.dao;


import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;

public interface MapDataDao {


	/*
	 * 데이트 추가
	 */
	public void addData(RequestMapData data);


	//////////////////


	/*
	 * 전체 뿌려주는 쿼리
	 */
	public ResponseMapData selectListData();
}
