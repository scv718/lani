package com.lani.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.lani.request.RequestMapData;


public interface FileService {

	boolean uploadFile(RequestMapData mapVo, MultipartFile file) throws IllegalStateException, IOException;

}
