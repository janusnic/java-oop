public class Main {
    //Выведите на экран все члены последовательности 2an-1–1, где a1=2, которые меньше 10000.
    public static void main(String[] args) {
        int a=2;
        for (int i = 0; a < 10000; a=2*a-1, i++) {
              System.out.print(a + " ");
            }
    }
}