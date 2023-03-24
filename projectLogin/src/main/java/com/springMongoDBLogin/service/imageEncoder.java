package com.springMongoDBLogin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class imageEncoder {
	
	public String encode(Path imagePath) throws IOException {
		byte[] readImageBytes = Files.readAllBytes(imagePath);
		String imageBase64EncodedBytes = Base64.getEncoder().encodeToString(readImageBytes);
		return new String(imageBase64EncodedBytes);
	}

}
