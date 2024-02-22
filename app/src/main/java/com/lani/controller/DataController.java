package com.lani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lani.request.RequestMapData;
import com.lani.service.DataService;

@RestController
public class DataController {

	@Autowired
	DataService dataService;

	@GetMapping("/")
	public String home() {

		return "Data Ready...";
	}

	@RequestMapping(value = {"/addMap"}, method = RequestMethod.POST)
	public String addMap(@RequestBody RequestMapData mapVo) {

		if(mapVo.getMarkerTitle() == null || mapVo.getNaverMapLink() == null) {
			return "false";
		}

		dataService.addMapData(mapVo);

		return "true";

	}
}
