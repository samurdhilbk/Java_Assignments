/**
 * Created by Samurdhi on 2016-09-02.
 */


import java.lang.*;

public class Julia extends Fractal{

    //class which characterizes the Julia set

    public Complex c; //the Julia set constant

    public Julia(double a,double b){
        super(-1,1,-1,1,1000);
        c=new Complex(a,b);
    }

    public Julia(){
        this(-0.4,0.6);
    }



}