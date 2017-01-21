/**
 * Created by Samurdhi on 2016-09-02.
 */

import java.lang.*;

public class Mandelbrot extends Fractal{

    //class which characterizes the Mandelbrot set

    public Mandelbrot(double minX,double maxX,double minY,double maxY,int numIterations){
        super(minX,maxX,minY,maxY,numIterations);
    }

    public Mandelbrot(){
        this(-1,1,-1,1,1000);
    }

    public Mandelbrot(double minX,double maxX,double minY,double maxY){
        this(minX,maxX,minY,maxY,1000);
    }


}