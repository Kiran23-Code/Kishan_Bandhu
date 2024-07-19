package com.tce.kisanbandhu.utils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class CommonUtils {
	
	public static String saveImage(String completeImageData) throws FileNotFoundException, IOException {
		String path = "C:\\Users\\Kiran Prince\\Desktop\\Input\\processing.jpg";
		String imageDataBytes = completeImageData.substring(completeImageData.indexOf(",")+1);
		byte[] data = Base64.getDecoder().decode(imageDataBytes);
		try (OutputStream stream = new FileOutputStream(path)) {
		    stream.write(data);
		}
		return path;
	}
	
}
