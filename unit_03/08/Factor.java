import java.util.Scanner;
public class Factor {
    public static void main(String[] args) {
        int n;
            Scanner scan = new Scanner(System.in);
             System.out.print("Введите факториал натурально числа n : ");
              n = scan.nextInt();
              if(n < 0){
                  System.out.println("Вы ввели отрицательный факториал!");
                  }
              else {
              int y=1;
              for (int i=1; i<=n; i++){
                  y=y*i;
              }
              System.out.print(n+"!=");
              System.out.print(y);
              }
    }
}