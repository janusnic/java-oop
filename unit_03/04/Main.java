public class Main {
    //Создайте программу, выводящую на экран первые 20 элементов последовательности 2 4 8 16 32 64 128 
    public static void main(String[] args) {
        for (int i = 2; i < 257; i = i+2) {
              System.out.print(i*i + " ");
            }
    }
}