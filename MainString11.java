
public class MainString11 {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String domain = ".ua";
        // создадим буфер с помощью String объекта
        StringBuffer buffer = new StringBuffer("ababahalamaga"); // "ababahalamaga"
        // вставим домен в конец
        buffer.append(domain); // "ababahalamaga.ua"
        System.out.println(buffer);
        // удалим домен
        buffer.delete(buffer.length() - domain.length(), buffer.length()); // "ababahalamaga"
        System.out.println(buffer);
        // вставим домен в конец на этот раз используя insert
        buffer.insert(buffer.length(), domain); // "ababahalamaga.ua"
        System.out.println(buffer);
    }

}
