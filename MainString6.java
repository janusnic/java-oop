
public class MainString6 {

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
    }

    }

}
