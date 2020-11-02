package com.dn.opencv_template_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat source = Imgcodecs.imread("/sdcard/source.jpg");
        Mat template = Imgcodecs.imread("/sdcard/template.jpg");

        System.out.println(source.height());

        Mat outputImage = new Mat();

        Imgproc.matchTemplate(source, template, outputImage, Imgproc.TM_CCOEFF);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;

        System.out.println(matchLoc.x);
        System.out.println(matchLoc.y);

        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(255, 255, 255));
        Imgcodecs.imwrite("/sdcard/result.jpg", source);


    }
}