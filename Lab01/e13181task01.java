import java.io.*;
import java.util.*;

class Task01{

    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
            int n;  //declare input as n
            boolean special=false;	//boolean variable to store whether n is special or not
            System.out.print("Enter a number: ");     //prompt for input
            n=inp.nextInt();    //scan input to variable n
            System.out.printf("%d is ",n);       
            if(n%15==0){    //check if special
                System.out.print("special");
                special=true;	//set special to true
            }
            else System.out.print("not special ");
            if(n>999 || (n%5==0 && n%6==0 && n%18!=0)){     //check if scary
                if(n>999 && !(n%5==0 && n%6==0 && n%18!=0)) System.out.println(", big and scary but not weird.");     //check if scary but not weird
                if(n<=999 && (n%5==0 && n%6==0 && n%18!=0)) System.out.println(", weird and scary but not big.");     //check if scary but not big
                if(n>999 && (n%5==0 && n%6==0 && n%18!=0)){     //check if both weird and scary
                    if(special)  System.out.println(" , big, weird and scary.");    
                    else System.out.println(", big, weird and scary.");
                }
            }
            else{   //check if not scary
                if(special) System.out.println(" but not scary.");   
                else System.out.println("or not scary.");   
            }

    }
}