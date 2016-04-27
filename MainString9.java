
public class MainString9 {

    /**
     * @param args
     */
    public static void main(String[] args) {

            String str1 = "Мурзик";
        String str2 = new String(str1);
        boolean isCat = str1 == str2;

        System.out.println(str1 + " == " + str2 + " -> " + isCat);

        String s1 = "hello";
        String s2 = "hello";
        String s3 = s1;
        String s4 = "h" + "e" + "l" + "l" + "o";
        String s5 = new String("hello");
        String s6 = new String(new char[]{'h', 'e', 'l', 'l', 'o'});

        System.out.println(s1 + " == " + s2 + ": " + (s1 == s2));
        // попробуйте и другие варианты
        // System.out.println(s1 + " equals " + s2 + ": " + (s1.equals(s2)));
        // System.out.println(s1 + " == " + s3 + ": " + (s1 == s3));
        // System.out.println(s1 + " equals " + s3 + ": " + (s1.equals(s3)));
        // System.out.println(s1 + " == " + s4 + ": " + (s1 == s4));
        // System.out.println(s1 + " equals " + s4 + ": " + (s1.equals(s4)));
        // System.out.println(s1 + " == " + s5 + ": " + (s1 == s5)); // false
        // System.out.println(s1 + " equals " + s5 + ": " + (s1.equals(s5)));
        // System.out.println(s1 + " == " + s6 + ": " + (s1 == s6)); // false
        // System.out.println(s1 + " equals " + s6 + ": " + (s1.equals(s6)));

    }

}
