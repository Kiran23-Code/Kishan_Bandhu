package com.tce.kisanbandhu.grade;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlobAnalysis {
	static int grade = -1;
	public static int detect(String path, String outputPath)
    {

        Mat src = Imgcodecs.imread(path);

        Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));


        Imgproc.threshold(src, dst, 80, 160, Imgproc.THRESH_BINARY_INV);

        createImageFile(dst, outputPath);
        Mat inputImgMat = Imgcodecs.imread(outputPath);
        Mat dstImgMat = inputImgMat.clone();

        // Converting to single channel array image
        Imgproc.cvtColor(inputImgMat, dstImgMat, Imgproc.COLOR_BGR2GRAY);

        String path1 = "C:\\Users\\Kiran Prince\\Desktop\\Output\\Inverse\\output.jpg";
        createImageFile(dstImgMat, path1);

        int whitePixels = Core.countNonZero(dstImgMat) / 10;

        if(whitePixels > 80000)
        {
        	System.out.println("1st Grade : " +whitePixels);
            grade = 1;
        }
        else if(whitePixels > 60000 && whitePixels < 80000)
        {
            System.out.println("2nd Grade : " +whitePixels);
            grade = 2;
        }
        else
        {
            System.out.println("3rd Grade : "+whitePixels);
            grade = 3;
        }
        return grade;
    }

    static void createImageFile(Mat mat, String outputPath) {
        try {
            BufferedImage bufferedImage = Threshold.Mat2BufferedImage(mat);
            File f = new File(outputPath);
            ImageIO.write(bufferedImage, "jpg", f);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
