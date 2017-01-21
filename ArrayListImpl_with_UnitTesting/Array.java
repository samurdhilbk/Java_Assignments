package com.company;

import java.lang.*;

public class Array{

    private int a[];
    private int length;
    public Array(){
        this.a=new int[0];  //initialize array with zero length
        this.length=0;
    }
    public Array(int n){
        this.a=new int[n];  //initialize array with length n
        this.length=n;
    }
    public void add(int e){
        int b[]=new int[length+1];  //initialize new array with length n+1
        for(int i=0;i<length;i++) b[i]=this.a[i]; //copy all initial elements to new array
        b[this.length]=e;   //copy last element to the final element
        this.a=b;   //assign the new array to the instance array
        this.length++;  //increment array size
        return;
    }
    public void add(int index,int e){
        try{
            int b[]=new int[length+1];  //initialize new array with length n+1
            for(int i=0;i<index;i++) b[i]=this.a[i]; //copy all elements before "index" to new array
            b[index]=e; //assign new element to the "index" element
            for(int i=index+1;i<length+1;i++) b[i]=this.a[i-1]; //copy the elements after "index" to the new array
            this.a=b;   //assign the new array to the instance array
            this.length++;  //increment array size
        }
        catch(Exception exp){
            System.out.println("This index does not exist in the array. Add aborted.");
        }
    }
    public int size(){
        return this.length; //return the array size
    }
    public void replace(int index,int d){
        try{
            this.a[index]=d;    //change the element at "index" to "d"
        }
        catch(Exception e){
            System.out.println("This index does not exist in the array. Replace aborted.");
        }
    }
    public void remove(int index){
        try{
            int b[]=new int[length-1]; //initialize new array with length n+1
            for(int i=0;i<index;i++) b[i]=this.a[i]; //copy all elements before "index" to new array
            for(int i=index+1;i<length;i++) b[i-1]=this.a[i];   //copy the elements after "index" to the new array
            this.a=b;   //assign the new array to the instance array
            this.length--;  //decrement array size
        }
        catch(Exception e){
            System.out.println("This index does not exist in the array. Remove aborted.");
        }
    }
    public boolean isEmpty(){
        if(this.length>0) return false;     //return false if array size id zero
        return true;    //else return true
    }
    public boolean contains(int d){
        for(int i=0;i<length;i++) if(this.a[i]==d) return true; //iterate and return true if the array contains element
        return false;   //else return false
    }
    public void trimToSize(int size){
        try{
            int b[]=new int[size]; //initialize new array with length size
            for(int i=0;i<size;i++) b[i]=this.a[i];//copy all elements before "size" to new array
            this.a=b;   //assign the new array to the instance array
            this.length=size;   //change array size to "size"
        }
        catch(Exception e){
            System.out.println("The trim size is invalid. Trim aborted.");
        }
    }
    public void clear(){
        try{
            this.a=new int[0];  //re-initialize array to a zero length array
            this.length=0;  //set the array size to zero
        }
        catch(Exception e){
            System.out.println("Array could not be cleared. Clear aborted.");
        }
    }
    public String toString(){
        String s="[";
        try{
            if(length==0) return s+="]";
            for(int i=0;i<this.length-1;i++) s+=this.a[i]+", ";
            s+=this.a[this.length-1]+"]";
        }
        catch(Exception e){
            System.out.println("Could not be converted to String. Conversion aborted.");
        }
        finally{
            return s;
        }
    }

    public int[] getArray(){
        return this.a;  //return the array object
    }
}