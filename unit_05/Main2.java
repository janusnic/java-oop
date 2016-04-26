public class Main2 {
    public static void pr() {
        System.out.println();
    }
    public static void pr(double d) {
        if((int)d == d) {
            System.out.print((int)d);
        } else {
            System.out.print(d);
        }
    }
    public static void pr(double[] m) {
        pr(m, " ");
    }
    public static void pr(double[] m, String s) {
        for(int i = 0; i < m.length; i++) {
            pr(m[i]);
            System.out.print(s);
        }
    }
    public static void main(String[] args) {
        double[] arrn = {1, 2.71, 3.14, 15, -5, 92, 0.5};
        double p = 3.0;
        int k = 13;
        pr(p); // вывод числа, без дробной части при возможности
        pr(); // переводит строку
        pr(arrn); // вывод числового массива в строку
        pr(); // переводит строку
        pr(arrn,", "); // вывод числового массива в строку через запятую
        pr(); // переводит строку
        pr(k); // вывод целого числа через автоприведение
    }
}