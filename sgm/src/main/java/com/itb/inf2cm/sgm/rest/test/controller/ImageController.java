package com.itb.inf2cm.sgm.rest.test.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.ImageService;

@RestController
@RequestMapping("/test/image")
public class ImageController {
	
	private ImageService imageService;
	
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@GetMapping("/image/{imageName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) {

		Resource file = imageService.loadImage(imageName);

		if (file == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/open-image/{imageName}")
    public ResponseEntity<Resource> openImage(@PathVariable String imageName) {
        try {
            Resource image = imageService.loadImage(imageName);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping(value="/save", consumes = "multipart/form-data")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveImage(
			@RequestParam(required = false) MultipartFile file) {

		imageService.save(file);

		return ResponseEntity.ok(new MessageResponse("Imagem aramzenada com sucesso!"));

	}

}
