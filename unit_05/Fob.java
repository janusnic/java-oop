public class Fib {
    static int fib (int n) {
          if (n==1 || n == 2) {
            return 1;
          }
          return fib (n-2) + fib (n-1);
        }
    public static void main(String[] arg) {
        System.out.print(fib(8));
    }
}