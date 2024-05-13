package com.lani.serviceimpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lani.dao.TestDao;

@Service
public class TestServiceimpl {



	@Autowired
	TestDao testDao;


	public HashMap<String, Object> test(){

		System.out.println(testDao.selectMinimumWowzaIdx());
		return null;


	}

}
