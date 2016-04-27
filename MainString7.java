
public class MainString7 {

    /**
     * @param args
     */
    public static void main(String[] args) {

       String aba = "ababa";
        String hala = "halamaga";
        // все просто и красиво
        //String abahala = aba + hala;
        
        String abahala = new StringBuilder().append(aba).append(hala).toString(); 
        // может быть использован StringBuffer
        
        System.out.println(abahala); // Your char had been found!
        String formatString = "We are printing double variable (%f), string ('%s') and integer variable (%d).";
        System.out.println(String.format(formatString, 2.3, "aba", 10));
        // We are printing double variable (2.300000), string ('aba') and integer variable (10).
    }

    }

}
