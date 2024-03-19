package com.lani.serviceimpl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lani.request.RequestMapData;
import com.lani.service.FileService;

@Service
public class FileServiceimpl implements FileService{


	@Override
	public boolean uploadFile(RequestMapData mapVo, MultipartFile file) throws IllegalStateException, IOException {

		try {

			String uploadPath = "C:\\workspace\\lani\\vue-app\\public\\img\\";

			String saveFileName = mapVo.getDate()+"_"+mapVo.getStore()+".jpg";

			File saveFile = new File(uploadPath, saveFileName);

			file.transferTo(saveFile);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


}
