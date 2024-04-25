package com.lani.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMapData {


	String date;
	String title;
	String store;
	String point_x;
	String point_y;
	String filePath;
	String main_img;

}
