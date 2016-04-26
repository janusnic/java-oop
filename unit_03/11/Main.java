import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
        /*
        Выведите на экран первые 11 членов последовательности Фибоначчи. 
        Напоминаем, что первый и второй члены последовательности равны единицам, 
        а каждый следующий — сумме двух предыдущих.

        */
      int a = 1;
        int b = 1;
        int n;
        int sum_fib;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        for(int i = 0; i < n; i++){
            sum_fib = a + b;
            a = b;
            b = sum_fib;
            System.out.print(sum_fib + " ");
        }
          
        
    }
}