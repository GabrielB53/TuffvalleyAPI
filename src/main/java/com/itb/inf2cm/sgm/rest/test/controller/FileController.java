package com.itb.inf2cm.sgm.rest.test.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class FileController {

	private static final String ROOT_IMAGE_PATH = "public/images/";

	// Endpoint to serve image file
	@GetMapping("/image/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws Exception {
		// Path to the image file
		Path path = Paths.get(ROOT_IMAGE_PATH + "/" + imageName);
		// Load the resource
		Resource resource = new UrlResource(path.toUri());
		// Return ResponseEntity with image content type
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
	}

	// Endpoint to serve PDF file
	@GetMapping("/file/{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws Exception {
		// Path to the PDF file
		Path path = Paths.get(ROOT_IMAGE_PATH + "/" + fileName);
		// Load the resource
		Resource resource = new UrlResource(path.toUri());
		// Return ResponseEntity with PDF content type
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
	}
}
