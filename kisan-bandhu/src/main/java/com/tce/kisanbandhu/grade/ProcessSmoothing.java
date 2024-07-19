package com.tce.kisanbandhu.grade;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import static org.opencv.imgcodecs.Imgcodecs.imread;


public class ProcessSmoothing {
	///  Global Variables
    int DELAY_CAPTION = 1500;
    int DELAY_BLUR = 100;
    int MAX_KERNEL_LENGTH = 31;

    Mat src = new Mat(), dst = new Mat();
    String windowName = "Filter Demo 1";

    public BufferedImage run(String filename) throws Exception {

//      Resizing an image
//        String filename = ((args.length > 0) ? args[0] : "C:\\Users\\Kiran Prince\\Desktop\\Project\\Grading\\2nd Grade\\1st grade\\Kiran2.jpg");
        Mat original = imread(filename);
        Size sz = new Size(3024,4032);
        Imgproc.resize( original, src, sz );

//        src = imread(filename,Imgcodecs.IMREAD_COLOR);
        if( src.empty() ) {
            System.out.println("Error opening image");
            System.out.println("Usage: ./Smoothing [image_name -- default ../data/lena.jpg] \n");
            System.exit(-1);
        }

//        if( displayCaption( "Original Image" ) != 0 ) { System.exit(0); }

        dst = src.clone();
        //! [gaussianblur]
        for (int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2) {
            Imgproc.GaussianBlur(src, dst, new Size(i, i), 0, 0);
        }

        return Mat2BufferedImage(dst);
    }

    int displayCaption(String caption) {
        dst = Mat.zeros(src.size(), src.type());
        Imgproc.putText(dst, caption,
                new Point(src.cols() / 4, src.rows() / 2),
                Imgproc.FONT_HERSHEY_COMPLEX, 1, new Scalar(255, 255, 255));

        return displayDst(DELAY_CAPTION);
    }

    int displayDst(int delay) {
        HighGui.imshow( windowName, dst );
        int c = HighGui.waitKey( delay );
        if (c >= 0) { return -1; }
        return 0;
    }

    static BufferedImage Mat2BufferedImage(Mat matrix)throws Exception {
        MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        byte ba[]=mob.toArray();

        BufferedImage bi= ImageIO.read(new ByteArrayInputStream(ba));
        return bi;
    }
}
