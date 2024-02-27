package com.lani.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RequestMapData {

	String date;
	String location;
	String store;
	String title;
	String point_x;
	String point_y;
	MultipartFile file;

}
