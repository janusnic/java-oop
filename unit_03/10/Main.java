import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
        
        /*
        Проверьте, является ли введённое пользователем с клавиатуры натуральное число — простым. 
        Постарайтесь не выполнять лишних действий (например, после того, как вы нашли хотя бы один 
        нетривиальный делитель уже ясно, что число составное и проверку продолжать не нужно). 
        Также учтите, что наименьший делитель натурального числа n, если он вообще имеется, 
        обязательно располагается в отрезке [2; √n].

        */
      int n;
            Scanner scan = new Scanner(System.in);
             System.out.print("Введите натуральное числа n : ");
             if(scan.hasNextInt()){ 
             n = scan.nextInt();

              System.out.print("Делителями числа "+n+" являются ");
              for(int i=n;i>0;i--){
                  int b=n%i;
                  if(b==0)
                      System.out.print(i+" ");
              }
          }
          else System.out.println("Ошибка. Введено не число");
          
        
    }
}