/**
 * Created by Samurdhi on 2016-09-02.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Fractal extends JFrame {


    //class which generalizes the common aspects of both the Mandelbrot and Julia sets

    protected double minX,minY,maxX,maxY;
    protected int canvasHeight,canvasWidth;
    protected int numIterations;
    protected BufferedImage canvas;
    protected int isSet[][];

    protected int numThreads;


    public Fractal(double minX,double maxX,double minY,double maxY,int canvasHeight,int canvasWidth,int numIterations,int numThreads){
        this.minX=minX;
        this.minY=minY;
        this.maxX=maxX;
        this.maxY=maxY;
        this.canvasHeight=canvasHeight;
        this.canvasWidth=canvasWidth;
        this.numIterations=numIterations;
        this.numThreads=numThreads;
        canvas=new BufferedImage(canvasWidth,canvasHeight,BufferedImage.TYPE_INT_RGB);
        this.isSet=new int[canvasWidth][canvasHeight];
    }

    public Fractal(double minX,double maxX,double minY,double maxY,int numIterations){
        this(minX,maxX,minY,maxY,800,800,numIterations,8);
    }


    @Override
    public void paint(Graphics g){
        g.drawImage(canvas,0,0,null);
    }

    //function to determine the inclusion/exclusion of points in the Mandelbrot or Julia set
    public void calculate(){


        Thread[] threads=new Thread[numThreads];

        for(int i=0;i<numThreads;i++){

            //We assume that the number of threads is even(this is a reasonable assumption as the number of cores in
            //most multi-processors is even). Now we divide the canvas into "numThreads" similar parts and carry out
            //the computation concurrently in each of these parts.
            int minI=(i/2)*canvasWidth/(numThreads/2);

            int maxI=(i/2+1)*canvasWidth/(numThreads/2);

            int minJ=(i%2==0)?0:(canvasHeight/2);   //set minJ according to the parity of i

            int maxJ=(i%2==1)?canvasHeight:(canvasHeight/2);    //set maxJ according to the parity of i

            Thread th=new Thread(new FractalThread(this,minI,maxI,minJ,maxJ));  //create new thread with the appropriate FractalThread object
            threads[i]=th;  //store thread in the array
            th.start(); //start the thread
        }

        try{
            //wait for all the threads to finish executing
            for(int i=0;i<numThreads;i++){
                threads[i].join();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    //function which encapsulates all that is needed to draw the fractal
    public void draw(){

        calculate(); //populate the isSet double array

        //assign colors to each pixel (i,j) according to the value of isSet[i][j]
        for(int i=0;i<canvasWidth;i++){
            for(int j=0;j<canvasHeight;j++){

                //color is set according to the HSB color model
                float s =1200f; //saturation
                float b = (isSet[i][j] !=0) ? 5.11f : 0; //brightness


                float h = (isSet[i][j]%1000)/150.0f; //hue

                canvas.setRGB(i,j,Color.getHSBColor(h, s, b).getRGB());
            }
        }

        // set other frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0,canvasWidth,canvasHeight);
        setVisible(true);

    }


    //getters and setters

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }
}