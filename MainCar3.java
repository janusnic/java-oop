package carsale;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.util.ArrayList;


public class MainCar {

    /**
     * @param args
     */
    public static void main(String[] args) {
                
        Scanner sc = new Scanner(System.in);
        
        /*
         * To read line or string from console use,
         * readLine method of BufferedReader class.
         */
         
         
         BufferedReader br =
         new BufferedReader(new InputStreamReader(System.in));
         
         String strLine = null;
         
         ArrayList<Sedan> arrayList = new ArrayList<Sedan>();
         
         
         System.out.println("Reading line of characters from console");
         System.out.println("Enter exit to quit or new to addnew or show to display");
         
         try
         {
               
                while( (strLine = br.readLine()) != null)
                {
                    if(strLine.equals("exit"))
                        break;

                   switch(strLine)  {
                   case "exit":
                       break;
                   case "new":
                       System.out.println("Enter id of car:" );  
                       int id = sc.nextInt();
                       System.out.println("Enter model of car:" );  
                       String model = sc.next();
                       System.out.println("Enter brand of car:" );  
                       String brand = sc.next();
                       System.out.println("Enter price of car:" );  
                       double price = sc.nextDouble();
                       System.out.println("Enter geartype of car:" );  
                       int geartype = sc.nextInt();
                       //
                       Sedan car = new Sedan(id, model, brand, price, geartype);
                       arrayList.add(car);
                       
                       car.display();
                       break;
                       
                    case "show":
                        
                        //System.out.println(arrayList);
                        
                        for (int i = 0; i < arrayList.size(); i++)
                        {
                            Sedan car1 = arrayList.get(i);
                            car1.display();
                        }
                        break;

                    default:
                        System.out.println("Enter exit to quit Enter new to add show to display"  + strLine);
                        break;
                   }
                }
               
                br.close();                    
                                       
         }
         catch(Exception e)
         {
                System.out.println("Error while reading line from console : " + e);
         }

    }

}
