public class Main {
    //Выведите на экран все двузначные члены последовательности 2an-1+200, где a1= –166.

    public static void main(String[] args) {
        int a=-166;
        for (int i = 2; i < 10; a=2*a+200, i++) {
              if (Math.abs(a)<100) { System.out.print(a + " ");}
            }
    }
}