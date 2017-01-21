import java.io.*;
import java.util.*;
import java.math.*;

class Task02{

    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        int r,g,b;      //declare variables for color input
        System.out.print("Enter the color: ");    //prompt for input
        //scan input into variables
        r=inp.nextInt();
        g=inp.nextInt();
        b=inp.nextInt();
        if(!(r>=0 && r<=255) || !(g>=0 && g<=255) || !(b>=0 && b<=255)){	//check whether input is valid i.e whether each color comp
        System.out.println("Enter valid input");	//print error message on console
        }
        else{
            int r1=255-r,g1=255-g,b1=255-b;     //caluculate initial compliments
        if(Math.abs(r1-r)<=32 && Math.abs(g1-g)<=32 && Math.abs(b1-b)<=32){	//if all three compliments are within the range of difference 32...
                r1=(r>=128)?r-128:r+128;   //adjust red compliment, if greater than or equal to 128 deduct 128 else add 128
                g1=(g>=128)?g-128:g+128;   //adjust green compliment, if greater than or equal to 128 deduct 128 else add 128
                b1=(b>=128)?b-128:b+128;   //adjust blue compliment, if greater than or equal to 128 deduct 128 else add 128
        }
            System.out.printf("The complement: %d %d %d\n",r1,g1,b1);  //Print the final compliments, if greater than or equal to 128 deduct 128 else add 128
        }

    }
}