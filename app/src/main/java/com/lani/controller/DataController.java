package com.lani.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lani.code.ResponseCode;
import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;
import com.lani.response.RestResponse;
import com.lani.service.DataService;
import com.lani.util.CheckUtil;

@RestController
public class DataController {

    private final Logger log = LoggerFactory.getLogger(DataController.class);

	@Autowired
	DataService dataService;

//	@Autowired
//	CheckUtil checkUtil;

	@GetMapping("/")
	public String Home() {


		return "Data Ready...";
	}

	@GetMapping("/test")
	public String test() {

		RequestMapData mapData = new RequestMapData();

		mapData.setDate("111");
		mapData.setTitle(null);
		mapData.setLocation(null);

		CheckUtil.parameterCheck(mapData);


		return "Data Ready...";
	}
	@GetMapping("/map")
	public List<ResponseMapData> map() {


		return dataService.sendMapData();
	}


	@PostMapping("/addMap")
	public RestResponse addMap(@RequestBody RequestMapData mapVo) {

//		if(mapVo.getLocation() == null || mapVo.getTitle() == null) {
//			log.debug("params Check : {}", mapVo);
//			return new RestResponse(false, ResponseCode.C900);
//		}

		try {

			System.out.println(mapVo);
			dataService.addMapData(mapVo);
		}catch (Exception e) {
			e.printStackTrace();
			return new RestResponse(false, ResponseCode.C900);
		}

		return new RestResponse(true, ResponseCode.C200);

	}
}
