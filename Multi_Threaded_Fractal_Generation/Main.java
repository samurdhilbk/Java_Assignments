/**
 * Created by Samurdhi on 2016-09-02.
 */

import java.lang.*;


public class Main{

    //Helper class to handle IO
    public static void main(String[] args) {

        int n=args.length;

        if(n==0){
            System.err.println("Please specify the nature of the Fractal you want to create.");
            return;
        }

        if(args[0].equals("Mandelbrot")){
            Mandelbrot mf;
            if(n==1){
                mf=new Mandelbrot();
                mf.draw();
            }
            else if(n==5){
                try{
                    mf=new Mandelbrot(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]),Double.parseDouble(args[4]));
                    mf.draw();
                }
                catch(NumberFormatException e) {
                    System.err.println("One or more of your numerical arguments is incorrect. Please check.");
                }


            }
            else if(n==6){

                try{
                    mf=new Mandelbrot(Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3]),Double.parseDouble(args[4]),Integer.parseInt(args[5]));
                    mf.draw();
                }
                catch(NumberFormatException e) {
                    System.err.println("One or more of your numerical arguments is incorrect. Please check.");
                }

            }
            else{
                System.err.println("Error in the number of inputs. The Mandelbrot Fractal can be called with either 0,4 or 5 arguments.");
            }
        }
        else if(args[0].equals("Julia")){
            Julia jf;
            if(n==1){

                jf=new Julia();
                jf.draw();

            }
            else if(n==3){

                try{
                    jf=new Julia(Double.parseDouble(args[1]),Double.parseDouble(args[2]));
                    jf.draw();
                }
                catch(NumberFormatException e) {
                    System.err.println("One or more of your numerical arguments is incorrect. Please check.");
                }

            }
            else{
                System.err.println("Error in the number of inputs. The Julia Fractal can be called with either 0 or 2 arguments.");
            }
        }
        else{
            System.err.println("Error in input type. Specify the type of fractal(Mandelbrot or Julia).");
        }
        
    }
}