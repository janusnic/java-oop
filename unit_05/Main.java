public class Main {
       /*
      Статические методы

        */

    public static double kvadk(int a) {
          double t;
          t = Math.pow(a, 0.5);
          return t;
        }
    
    public static void main(String[] args){
        int a = 25;
        System.out.println(kvadk(a));
        // 5.0
        System.out.println(a);
        // 25
    }
 }