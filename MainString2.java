
public class MainString2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String aba = "ababahalamaga";
        // получить длину строки
        int length = aba.length();
        // теперь можно узнать есть ли символ символ 'h' в "ababahalamaga"
        char searchChar = 'h';
        boolean isFound = false;
        for (int i = 0; i < length; ++i) {
            if (aba.charAt(i) == searchChar) {
                isFound = true;
                break; // первое вхождение
            }
        }
        System.out.println(message(isFound)); // Your char had been found!
        // ой, забыл, есть же метод indexOf
        System.out.println(message(aba.indexOf(searchChar) != -1)); // Your char had been found!
    }

    private static String message(boolean b) {
        return "Your char had" + (b ? " " : "n't ") + "been found!";
    }

}
