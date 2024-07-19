package com.tce.kisanbandhu.grade;

import java.awt.image.BufferedImage;

import nu.pattern.OpenCV;

public class ProcessImage {
	
	public static int exec(String path) {
//        String filename = "C:\\Users\\Kiran Prince\\Desktop\\Project\\Grading\\2nd Grade\\Kiran3.jpg";
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		OpenCV.loadLocally();
        try {
            BufferedImage image = new ProcessSmoothing().run(path);
            String greyscalePath = "C:\\Users\\Kiran Prince\\Desktop\\Output\\GrayScale\\greyscale5.jpg";
            GreyScaleConverter.convert(image, greyscalePath);
            String thresholdPath = "C:\\Users\\Kiran Prince\\Desktop\\Output\\Threshold\\threshold5.jpg";
            Threshold.convert(greyscalePath, thresholdPath);
            String analysisPath = "C:\\Users\\Kiran Prince\\Desktop\\Output\\BlobAnalysis\\analysis5.jpg";
            return BlobAnalysis.detect(thresholdPath, analysisPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
