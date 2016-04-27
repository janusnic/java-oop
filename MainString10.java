
public class MainString10 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        StringBuffer firstBuffer = new StringBuffer(); // capacity = 16
        StringBuffer secondBuffer = new StringBuffer("ababahalamaga"); // capacity = str.length() + 16
        StringBuffer thirdBuffer = new StringBuffer(secondBuffer); // параметр - любой класс, что реализует CharSequence
        StringBuffer fourthBuffer = new StringBuffer(50); // передаем capacity

        System.out.println(secondBuffer);
        System.out.println(secondBuffer.length());
        System.out.println(thirdBuffer);
        System.out.println(thirdBuffer);
    }

}
