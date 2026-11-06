package com.itb.inf2cm.sgm.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
public class ImageService {
	
	private static final String ROOT_IMAGE_PATH = "public/images/";
	
	private final Path rootPath = Paths.get(ROOT_IMAGE_PATH);
	
	
	public Resource loadImage(String imageName) {
		try {
			Path file = rootPath.resolve(imageName);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read file: " + imageName);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
	@Transactional
	public void save(MultipartFile file) {
		
		if (file.isEmpty()) {
			throw new RuntimeException("Failed to store empty file.");
		}
		
		String originalName = file.getOriginalFilename();
		String imageName = new Date().getTime() + "_" + originalName;
		
		// String sourceTable = enumSourceTable.toString().toLowerCase();
		// String imageType = file.getContentType();
		
		Path newFile = Paths.get(ROOT_IMAGE_PATH + "/" + imageName);
		try {
			//Files.createDirectories(newFile.getParent());
			Files.write(newFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//String imagePath = newFile.toAbsolutePath().toString();
		//System.out.println(imagePath);
		
	}
	

}
