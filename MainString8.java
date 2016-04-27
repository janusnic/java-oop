
public class MainString8 {

    /**
     * @param args
     */
    public static void main(String[] args) {

            String testString = "Котёнок";

        if (testString.compareTo("котёнок") == 0) {
            System.out.println("Строки равны");
        } else {
            System.out.println("Строки не равны. Возвращено"
                    + testString.compareTo("котёнок")); // возвращает -32
        }
        String[] poem = { "Мы", "везём", "с", "собой", "кота" };

        for (int j = 0; j < poem.length; j++) {
            for (int i = j + 1; i < poem.length; i++) {
                if (poem[i].compareTo(poem[j]) < 0) {
                    String temp = poem[j];
                    poem[j] = poem[i];
                    poem[i] = temp;
                }
            }
            System.out.println(poem[j]);
        }
    }

    }

}
