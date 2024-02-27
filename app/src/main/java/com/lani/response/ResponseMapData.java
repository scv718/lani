package com.lani.response;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ResponseMapData {

	String date;
	String location;
	String title;
	String store;
	String point_x;
	String point_y;
	MultipartFile file;

}
