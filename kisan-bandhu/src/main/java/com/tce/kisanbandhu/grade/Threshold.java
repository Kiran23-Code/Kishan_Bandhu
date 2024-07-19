package com.tce.kisanbandhu.grade;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;


public class Threshold {
	 public static void convert(String file, String outputPath)
	    {
	        Mat src = Imgcodecs.imread(file,Imgcodecs.IMREAD_COLOR);
	        if( src.empty() ) {
	            System.out.println("Error opening image");
	 //           System.out.println("Usage: ./Smoothing [image_name -- default ../data/lena.jpg] \n");
	            System.exit(-1);
	        }

	        Mat dst = src.clone();
	 //       Imgproc.threshold(src, dst, 177, 200, Imgproc.THRESH_BINARY);

	        Imgproc.threshold(src, dst, 80, 160, Imgproc.THRESH_BINARY);
//	        HighGui.imshow("Contours operation", dst);
//	        HighGui.waitKey();

	        try {
	            BufferedImage bufferedImage = Mat2BufferedImage(dst);
	            File f = new File(outputPath);
	            ImageIO.write(bufferedImage, "jpg", f);
	        }catch (IOException e) {
	                System.out.println(e);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    static BufferedImage Mat2BufferedImage(Mat matrix)throws Exception {
	        MatOfByte mob=new MatOfByte();
	        Imgcodecs.imencode(".jpg", matrix, mob);
	        byte ba[]=mob.toArray();

	        BufferedImage bi= ImageIO.read(new ByteArrayInputStream(ba));
	        return bi;
	    }
}
