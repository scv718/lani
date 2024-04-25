package com.lani.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lani.code.ResponseCode;
import com.lani.request.RequestMapData;
import com.lani.response.ResponseMapData;
import com.lani.response.RestResponse;
import com.lani.service.DataService;
import com.lani.service.FileService;
import com.lani.service.RedisDataService;
import com.lani.util.CheckUtil;

@RestController
public class DataController {

    private final Logger log = LoggerFactory.getLogger(DataController.class);

	@Autowired
	DataService dataService;

	@Autowired
	FileService fileServie;

	@Autowired
	RedisDataService redisDataService;

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


//		return dataService.sendMapData();
		return redisDataService.sendMapData();
	}


	@PostMapping("/addMap")
	public RestResponse addMap(@RequestParam("data") String jsonData, @RequestParam("file") MultipartFile file) {

//		if(mapVo.getLocation() == null || mapVo.getTitle() == null) {
//			log.debug("params Check : {}", mapVo);
//			return new RestResponse(false, ResponseCode.C900);
//		}
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			RequestMapData mapVo = objectMapper.readValue(jsonData, RequestMapData.class);
			if(fileServie.uploadFile(mapVo, file)) {
				dataService.addMapData(mapVo);
			}

		}catch (Exception e) {
			e.printStackTrace();
			return new RestResponse(false, ResponseCode.C900);
		}

		return new RestResponse(true, ResponseCode.C200);

	}
}
