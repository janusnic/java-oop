
public class MainString3 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String javaAba = "ababahalamaga".concat(".ua").concat("/aba").concat("/java");
        System.out.println(javaAba); // получим "ababahalamaga.ua/hub/java"
        int length = javaAba.length();
        char searchChar = 'h';
        boolean isFound = false;
        for (int i = 0; i < length; ++i) {
            if (javaAba.charAt(i) == searchChar) {
                isFound = true;
                break; // первое вхождение
            }
        }
        System.out.println(message(isFound)); // Your char had been found!
        // ой, забыл, есть же метод indexOf
        System.out.println(message(javaAba.indexOf(searchChar) != -1)); // Your char had been found!
    }
    // перепишем наш метод используя concat
    private static String message(boolean b) {
        return "Your char had".concat(b ? " " : "n't ").concat("been found!");
    }
    
}
