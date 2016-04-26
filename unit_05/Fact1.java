public class Fact1 {
    static int fact (int n) {
          if (n==1) {
            return 1;
          } else if (n==2) {
            return 2;
          } else {
            return fact(n-1) * n;
          }
        }
    public static void main(String[] arg) {
        System.out.print(fact(8));
    }
}