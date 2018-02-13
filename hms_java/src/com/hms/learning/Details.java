package com.hms.learning;
import java.util.ArrayList;
public class Details {

   public static void main(String a[]){
     ArrayList<String> al = new ArrayList<String>();

     //Adding elements to the ArrayList
     al.add("Apple");
     al.add("Orange");
     al.add("Mango");
     al.add("Grapes");
     System.out.println("ArrayList: "+al);

     ArrayList<String> al2 = (ArrayList<String>)al.clone();
     System.out.println("Shallow copy of ArrayList: "+ al2);

     //add and remove on original ArrayList
     al.add("Fig");
     al.remove("Orange");

     //Display of both ArrayLists after add & remove
     System.out.println("Original ArrayList:"+al);
     System.out.println("Cloned ArrayList:"+al2);
   }
}