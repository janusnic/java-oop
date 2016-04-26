class StaticModifier 
{ 
static int i = 10; 
int j ; 

StaticModifier() 
       { 
        j =20; 
       } 
} 

public class StaticDemo {
       /*
      Статические методы

        */

    public static void main(String args[]) 
    { 
        int i=77;
        System.out.println(i+StaticModifier.i); 
        StaticModifier s = new StaticModifier(); 
        int j=88;
        System.out.println(j+s.j); 
    } 
 }