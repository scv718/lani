package com.lani.event;

import org.springframework.context.ApplicationEvent;

public class DatabaseInsertEvent extends ApplicationEvent {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String tableName;

    public DatabaseInsertEvent(Object source, String tableName) {
        super(source);
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }


//    // 새로운 데이터를 조회하는 메서드
//    public List<ResponseMapData> fetchNewData() {
//
//    	System.out.println("인서트 발생 데이터 조회");
//    	MapDataDao dataDao = (MapDataDao) BeanUtils.getBean(MapDataDao.class);
//    	List<ResponseMapData> listData = dataDao.selectListData();
//        return listData;
//    }
}