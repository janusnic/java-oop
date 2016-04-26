    
public class Reload {

static void pr( double a) {
          System.out.println(a);
        }

static void pr(String a) {
          System.out.println(a);
        }

static void pr(int[] a) {
          
    for (int i=0; i<a.length; i++) {
            System.out.print(a[i]+" ");
          }

       System.out.println();
        }
    
    public static void main(String args[]) 
    { 
        int a = 5;
        int m[]  = {1, 2, 8, 3};
        String s = "Мир";
        pr(a); //работает исходный метод
        pr(a+s); // 5 мир, работает первая перегрузка
        pr(m); // 1 2 8 3
    //  pr(m+a); // ошибка  
    } 
 }