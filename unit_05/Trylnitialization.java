public class Trylnitialization {
       /*
      Статические методы

        */

    static {
        System.out.println("Static");
    }

    {
        System.out.println("Non-static block");
    }

    public Trylnitialization() {
        System.out.println("Constructor");
    }
    
    public static void main(String args[]) 
    { 
        
    } 
 }