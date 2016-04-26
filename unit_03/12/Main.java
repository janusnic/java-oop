import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        /*
        Для введённого пользователем с клавиатуры натурального числа посчитайте сумму 
        всех его цифр (заранее не известно сколько цифр будет в числе).
        */
      
        Scanner s = new Scanner(System.in);
        int sum = 0;
        
        for(int n = s.nextInt(); n != 0; n /= 10)
        { 
            sum = sum + (n % 10); 
        } 
        System.out.println(sum + " ");
        
    }
}