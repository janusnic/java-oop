import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
      int n;
            Scanner scan = new Scanner(System.in);
             System.out.print("Введите натуральное числа n : ");
              n = scan.nextInt();

              int y=1;
              for (int i=1; i<=n; i++){
                  if (n%i==0)
                  {
              
              System.out.print(i+", ");
                  }
              }
        
    }
}