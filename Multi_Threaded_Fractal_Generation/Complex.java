/**
 * Created by Samurdhi on 2016-09-02.
 */
public class Complex {


    //Helper class to handle complex number calculations

    public double x, y;

    public Complex(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Complex add(Complex a){
        return new Complex(this.x+a.x,this.y+a.y);
    }

    public Complex subtract(Complex a){
        return new Complex(this.x-a.x,this.y-a.y);
    }

    public Complex multiply(Complex a){
        return new Complex(this.x*a.x-this.y*a.y,this.x*a.y+this.y*a.x);
    }

    public Complex square(){
        return new Complex(this.x*this.x - this.y*this.y, 2*this.x*this.y);
    }

    public double abss(){
        return this.x*this.x+this.y*this.y;
    }
}
