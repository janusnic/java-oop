import java.nio.charset.Charset;
public class MainString1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String aba = "ababahalamaga";
        
        System.out.print("ababahalamaga"); // создали объект и вывели его значение
        System.out.println(aba);
        
        char[] abaAsArrayOfChars = {'a', 'b', 'a', 'b', 'a', 'h', 'a', 'l', 'a', 'm', 'a', 'g', 'a'};

        byte[] abaAsArrayOfBytes = {97, 98, 97, 98, 97, 104, 97, 98, 114};
        
        String first = new String();
        String second = new String(aba);
        
        String third = new String(abaAsArrayOfChars); // "ababahalamaga"
        System.out.println(third);
        String fourth = new String(abaAsArrayOfChars, 0, 4); // "aba"
        System.out.println(fourth);
        String fifth = new String(abaAsArrayOfBytes, Charset.forName("UTF-16BE")); // кодировка нам явно не подходит "桡扲慨慢�"
        System.out.println(fifth);
        String sixth = new String(new StringBuffer(aba));
        System.out.println(sixth);
        String seventh = new String(new StringBuilder(aba));
        System.out.println(seventh);
 

    }

}
