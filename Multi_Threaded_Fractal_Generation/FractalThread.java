/**
 * Created by Samurdhi on 2016-09-04.
 */
public class FractalThread implements Runnable{


    //class which handles the fractal-specific calculations, as a thread

    private static final double EPS=0.000001;   //precision when making comparisons

    private Fractal f;
    private int minI, maxI, minJ, maxJ;
    private double minX,minY,maxX,maxY;
    private int canvasHeight,canvasWidth;
    private int numIterations; 


    public FractalThread(Fractal f,int minI,int maxI,int minJ,int maxJ){
        this.f=f;
        this.minI=minI;
        this.maxI=maxI;
        this.minJ=minJ;
        this.maxJ=maxJ;
        minX=f.minX;
        maxX=f.maxX;
        minY=f.minY;
        maxY=f.maxY;
        canvasHeight=f.canvasHeight;
        canvasWidth=f.canvasWidth;
        numIterations=f.numIterations;
    }

    @Override
    public void run(){

        if(f instanceof Mandelbrot){    //if the fractal is a Mandelbrot Fractal

            for(int i=minI;i<maxI;i++){
                for(int j=minJ;j<maxJ;j++){

                    //calculate the position in the complex plane
                    double x=minX+(maxX-minX)*((double)i)/((double)canvasWidth);
                    double y=minY+(maxY-minY)*((double)j)/((double)canvasHeight);

                    //initialize zn and c
                    Complex zn=new Complex(0.0,0.0),c=new Complex(x,y);

                    f.isSet[i][j]=0;
                    for(int k=0;k<numIterations;k++){
                        if(zn.abss()-4.0>EPS){
                            f.isSet[i][j]=k+1;
                            break;
                        }
                        zn=zn.square().add(c);
                    }
                }
            }
        }

        else if(f instanceof Julia){    //if the fractal is a Julia Fractal
            for(int i=minI;i<maxI;i++){
                for(int j=minJ;j<maxJ;j++){

                    //calculate the position in the complex plane
                    double x=minX+(maxX-minX)*((double)i)/((double)canvasWidth);
                    double y=minY+(maxY-minY)*((double)j)/((double)canvasHeight);

                    //initialize zn and c
                    Complex zn=new Complex(x,y);

                    f.isSet[i][j]=0;
                    for(int k=0;k<numIterations;k++){
                        if(zn.abss()-4.00000>EPS){
                            f.isSet[i][j]=k+1;
                            break;
                        }
                        zn=zn.square().add(((Julia)f).c);
                    }

                }
            }
        }

        else{
            System.err.println("Thread cannot be applied to object of this type");
        }
    }


}
