public class Main {
    // факториал

    public static void main(String[] args) {
        int y=1;
        int n=8;
        for (int i=1; i<=n; i++){
            y=y*i;
        }
        System.out.print(n+"!=");
        System.out.print(y);
    }
}