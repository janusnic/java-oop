# java Unit 06

класс Object
============
Любой класс в Java является наследниом класса Object и от этого родительского класса получает ряд готовых методов. 

метод toString
--------------
Одним из стандартных методов, определенных в java.lang.Object является toString. Этот метод используется для получения строкового представления объекта. Вы можете (обычно должны) переопределить этот метод для записываемых вами классов.

class Point:
------------
        class Point {

            private double x; // абсцисса точки
            private double y; // ордината точки

            // конструктор по умолчанию, создающий точку в начале координат
            public Point() {
                this.x = 0.0;
                this.y = 0.0;
            }
            // конструктор, создающий точку с указанными координатами
            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }  
        }

        public class MainPoint0 {
            /**
             * @param args
             */
            public static void main(String[] args) {

                Point p1 = new Point(1,1);
                // использовать Object.toString() по умолчанию
                System.out.println(mp);
           }

        }


    public class MainPoint {
        public static void main(String args[]) {
            Point p1 = new Point(1, 1);
    
            // использовать Object.toString() по умолчанию
    
            System.out.println(p1);
    
            // аналогично предыдущему, показывая
            // функцию toString() по умолчанию
    
            System.out.println(p1.getClass().getName() 
                + "@" 
                + Integer.toHexString(p1.hashCode()));
    
            // неявный вызов toString() объекта
            // как часть соединения строк
    
            String s = p1 + " testing";
            System.out.println(s);
    
            // аналогично предыдущему, за исключением того,
            // что ссылка на объект равна null
    
            p1 = null;
            s = p1 + " testing";
            System.out.println(s);
        }
    }

В программе MainPoint определяется класс Point для представления точек X,Y. В ней не определяется метод toString для класса. Создается экземпляр класса и затем распечатывается. После выполнения программы MainPoint вы должны получить примерно следующий результат:

    Point@784d2125
    Point@784d2125
    Point@784d2125 testing
    null testing


Распечатка произвольного объекта класса
---------------------------------------
Такие методы библиотеки, как System.out.println, не знают ничего о классе Point или его объектах. Поэтому возникает вопрос, каким образом возможно преобразовать такой объект в строковую форму и вывести на печать.

Ответ заключается в том, что println вызывает метод java.io.PrintStream.print(Object), который затем вызывает метод String.valueOf. Метод String.valueOf является очень простым:

    public static String valueOf(Object obj) {
        return (obj == null) ? "null" : obj.toString();
    }
При вызове println со ссылкой на объект Point, метод String.valueOf преобразует объект в строку. String.valueOf сначала проверяет ссылку, чтобы убедиться, что она не равна null. Затем он вызывает метод toString для объекта. Поскольку класс Point не имеет метода toString, используется метод в java.lang.Object по умолчанию.

Что же возвращает метод toString по умолчанию в качестве строкового значения? Возвращаются объединенные в одну строку название класса, "@" и шестнадцатиричное представление хэшкода объекта. В методе hashCode в Object по умолчанию обычно реализуется преобразование адреса памяти объекта в числовое значение. Поэтому ваш результат может отличаться от приведенного.

при использовании "+" для соединения строки с объектом, вызывается toString для преобразования объекта в строку. Чтобы увидеть это, вы должны взглянуть на байткод для MainPoint. Байткод в подходящей для чтения форме можно получить, выполнив следующую команду:

    javap -c . MainPoint

Если вы посмотрите на байткод, вы увидите, что часть его содержит создание объекта StringBuffer и использование StringBuffer.append(Object) для добавления к нему объекта p1. StringBuffer.append(Object) реализуется очень просто:

    public synchronized StringBuffer append(Object obj) {
        return append(String.valueOf(obj));
    }

String.valueOf вызывает toString с объектом для получения его строкового значения.

Собственные методы toString
----------------------------

Как можно написать свои собственные методы toString:

    class Point {
        private final int x, y;
    
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        public String toString() {
            return x + " " + y;
        }
    }
    
    public class MainPoint1 {
        public static void main(String args[]) {
            Point mp = new Point(37, 47);
    
            // вызвать Point.toString()
    
            System.out.println(mp);
    
            // вызвать toString() и
            // получить из него значение X
    
            String s = mp.toString();
            String t = s.substring(0, s.indexOf(' '));
            int x = Integer.parseInt(t);
            System.out.println(t);
        }
    }

Результат выполнения этой программы:

    37 47
    37
Метод toString в этом примере действительно работает, но есть парочка проблем. Одной из них является то, что здесь нет никакого описательного текста. Все что вы видите - это непонятная строка "37 47". Вторая проблема - значения X,Y в объектах Point имеют спецификатор доступа private. Не существует другого способа получить к ним доступ кроме выборки из строки, возвращенной из toString. 


Вот другой подход для записи метода toString, который устраняет проблемы предыдущего примера:

    class Point {
        
        private double x; // абсцисса точки
        private double y; // ордината точки

        // конструктор по умолчанию, создающий точку в начале координат
        public Point() {
            this.x = 0.0;
            this.y = 0.0;
        }
        // конструктор, создающий точку с указанными координатами
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        } 

        public String toString() {
                return "X=" + x + " " + "Y=" + y;
        }

        public double getX() {
            return x;
        }
        
        public double getY() {
           return y;
        } 
    }
   
    public class MainPoint3 {
        public static void main(String args[]) {
            Point mp = new Point(37, 47);
    
            // вызвать Point.toString()
    
            System.out.println(mp);
    
            // получить значения X,Y при помощи методов доступа
    
            double x = mp.getX();
            double y = mp.getY();
            System.out.println(x);
            System.out.println(y);
        }
    }

Результат работы программы:

    X=37 Y=47
    37
    47
В этом примере в вывод добавляется некоторый описательный текст и определяются два метода доступа для получения значений X,Y. В общем случае при написании метода toString формат возвращаемой строки должен содержать все содержимое объекта. Ваш метод toString должен, также, содержать описание для каждого поля. Также должен иметься способ получения значений полей объекта без извлечения из строки. Обратите внимание, что использование "+" в toString для построения возвращаемого значения не всегда является самым эффективным подходом. Возможно, вы захотите использовать вместо этого StringBuffer.

Реализация строк на Java представлена тремя основными классами: String, StringBuffer, StringBuilder.

String
======
Строка — объект, что представляет последовательность символов. Для создания и манипулирования строками Java платформа предоставляет общедоступный финальный (не может иметь подклассов) класс java.lang.String. Данный класс является неизменяемым (immutable) — созданный объект класса String не может быть изменен. Можно подумать что методы имеют право изменять этот объект, но это неверно. Методы могут только создавать и возвращать новые строки, в которых хранится результат операции. 
Неизменяемость строк предоставляет ряд возможностей:
---------------------------------------------------
- использование строк в многопоточных средах (String является потокобезопасным (thread-safe) )
- использование String Pool (это коллекция ссылок на String объекты, используется для оптимизации памяти)
- использование строк в качестве ключей в HashMap (ключ рекомендуется делать неизменяемым)

Создание
--------
Мы можем создать объект класса String несколькими способами:

1. Используя строковые литералы:

String aba = "ababahalamaga";

Строковый литерал — последовательность символов заключенных в двойные кавычки. Важно понимать, что всегда когда вы используете строковой литерал компилятор создает объект со значением этого литерала:

System.out.print("ababahalamaga"); // создали объект и вывели его значение

2. С помощью конструкторов:

    String aba = "ababahalamaga";
    char[] abaAsArrayOfChars = {'a', 'b', 'a', 'b', 'a', 'h', 'a', 'l', 'a', 'm', 'a', 'g', 'a'};
    byte[] abaAsArrayOfBytes = {97, 98, 97, 98, 97, 104, 97, 98, 114};
     
    String first = new String();
    String second = new String(aba);

Если копия строки не требуется явно, использование этих конструкторов нежелательно и в них нет необходимости, так как строки являются неизменными. Постоянное строительство новых объектов таким способом может привести к снижению производительности. Их лучше заменить на аналогичные инициализации с помощью строковых литералов.

String third = new String(abaAsArrayOfChars); // "ababahalamaga"
String fourth = new String(abaAsArrayOfChars, 0, 4); // "abab"

Конструкторы могут формировать объект строки с помощью массива символов. Происходит копирование массива, для этого используются статические методы copyOf и copyOfRange (копирование всего массива и его части (если указаны 2-й и 3-й параметр конструктора) соответственно) класса Arrays, которые в свою очередь используют платформо-зависимую реализацию System.arraycopy.

    import java.nio.charset.Charset;

String fifth = new String(abaAsArrayOfBytes, Charset.forName("UTF-16BE")); // кодировка нам явно не подходит "桡扲慨慢�"

Можно также создать объект строки с помощью массива байтов. Дополнительно можно передать параметр класса Charset, что будет отвечать за кодировку. Происходит декодирование массива с помощью указанной кодировки (если не указано — используется Charset.defaultCharset(), который зависит от кодировки операционной системы) и, далее, полученный массив символов копируется в значение объекта.

        String sixth = new String(new StringBuffer(aba));
        String seventh = new String(new StringBuilder(aba));

Ну и наконец-то конструкторы использующие объекты StringBuffer и StringBuilder, их значения (getValue()) и длину (length()) для создания объекта строки. 

Длина
-----
Важной частью каждой строки есть ее длина. Узнать ее можно обратившись к объекту String с помощью метода доступа (accessor method) length(), который возвращает количество символов в строке, например:

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
        // есть же метод indexOf
        System.out.println(message(aba.indexOf(searchChar) != -1)); // Your char had been found!
    }

    private static String message(boolean b) {
        return "Your char had" + (b ? " " : "n't ") + "been found!";
    }

Конкатенация
------------
Конкатенация — операция объединения строк, что возвращает новую строку, что есть результатом объединения второй строки с окончанием первой. Операция для объекта String может быть выполнена двумя способами:

1. Метод concat

    String javaAba = "ababahalamaga".concat(".ua").concat("/aba").concat("/java");
    System.out.println(javaAba); // получим "ababahalamaga.ua/hub/java"
    // перепишем наш метод используя concat
    private static String message(boolean b) {
        return "Your char had".concat(b ? " " : "n't ").concat("been found!");
    }

метод concat не изменяет строку, а лишь создает новую как результат слияния текущей и переданной в качестве параметра. метод возвращает новый объект String, поэтому возможны такие длинные «цепочки».

2. Перегруженные операторы "+" и "+="

    String aba = "ababa" + "halamaga"; // "ababahalamaga"
    aba += ".ua"; // "ababahalamaga.ua"

Это одни с немногих перегруженных операторов в Java — язык не позволяет перегружать операции для объектов пользовательских классов. Оператор "+" не использует метод concat, тут используется следующий механизм:

    String aba = "ababa";
    String hala = "halamaga";
    // все просто и красиво
    String abahala = aba + hala;
    // а на самом деле
    String abahala = new StringBuilder().append(aba).append(hala).toString(); // может быть использован StringBuffer

Используйте метод concat, если слияние нужно провести только один раз, для остальных случаев рекомендовано использовать или оператор "+" или StringBuffer / StringBuilder. Также стоит отметить, что получить NPE (NullPointerException), если один с операндов равен null, невозможно с помощью оператора "+" или "+=", чего не скажешь о методе concat, например:

    String string = null;
    string += " ababahalamaga"; // null преобразуется в "null", в результате "null ababahalamaga"
    string = null;
    string.concat("s"); // логично что NullPointerException

Форматирование
--------------
Класс String предоставляет возможность создания форматированных строк. За это отвечает статический метод format, например:

    String formatString = "We are printing double variable (%f), string ('%s') and integer variable (%d).";
    System.out.println(String.format(formatString, 2.3, "aba", 10));
    // We are printing double variable (2.300000), string ('aba') and integer variable (10).

Методы
------
public char charAt (int index)
------------------------------
Возвращает символ с указанным смещением в этой строке. Отсчёт идет от 0. Не надо использовать отрицательные и несуществующие значения. Для извлечения нескольких символов используйте getChars().


    String testString = "Котёнок";
    char myChar = testString.charAt(2);
    tv.setText(Character.toString(myChar)); // выводит третий символ - т


public int codePointAt(int index)
---------------------------------
Возвращает Unicode-символ в заданном индексе


    String testString = "Котёнок";
    int myChar = testString.codePointAt(3);
    tv.setText(String.valueOf(myChar)); // возвращает 1105


public int codePointBefore(int index)
-------------------------------------
Возвращает Unicode-символ, который предшествует данному индексу


    String testString = "Котёнок";
    int myChar = testString.codePointBefore(4);

    
public int codePointCount(int start, int end)

Вычисляет количество Unicode-символов между позициями start и end


    String testString = "Котёнок";
    int myChar = testString.codePointCount(0, 3);


public int compareTo(String string)

Сравнивает указанную строку, используя значения символов Unicode и вычисляет, какая из строк меньше, равна или больше следующей. Может использоваться при сортировке. Регистр учитывается. Если строки совпадают, то возвращается 0, если меньше нуля, то вызывающая строка меньше строки string, если больше нуля, то вызывающая строка больше строки string. Слова с большим регистром стоят выше слова с нижним регистром.


    String testString = "Котёнок";

    if (testString.compareTo("котёнок") == 0) {
        System.out.println("Строки равны");
    } else {
        System.out.println("Строки не равны. Возвращено"
                + testString.compareTo("котёнок")); // возвращает -32
    }
Отсортируем массив строк через пузырьковую сортировку.


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
В результате мы получим:


    Мы
    везём
    кота
    с
    собой
от перемены мест слагаемых сумма сортировки кота не меняются.

public int compareToIgnoreCase (String string)
Сравнивает указанную строку, используя значения символов Unicode, без учета регистра.


    String testString = "Котёнок";

    if (testString.compareToIgnoreCase("котёнок") == 0) {
        System.out.println("Строки равны"); // слова одинаковы, если не учитывать регистр
    } else {
        System.out.println("Строки не равны. Возвращено"
                + testString.compareTo("котёнок"));
    }

public String concat (String string)
Объединяет строку с указанной строкой. Возвращается новая строка, которая содержит объединение двух строк. Обратите внимание, что само имя метода содержит кота!


    String testString = "Сук";
    String newString = testString.concat("кот");
    tv.setText(newString);
Метод выполняет ту же функцию, что и оператор + и можно было написать Сук + кот. Но настоящий кошатник будет использовать "кошачий" метод.

public boolean contains (CharSequence cs)
Определяет, содержит ли строка последовательность символов в CharSequence


    String testString = "котёнок";

    if(testString.contains("кот")){
        System.out.println("В слове котёнок содержится слово кот!");
    }

public boolean contentEquals(CharSequence cs)
Сравнивает CharSequence с этой строкой.


public boolean contentEquals(StringBuffer strbuf)
Сравнивает StringBuffer с этой строкой


public static String copyValueOf (char[] data, int start, int length)
Создаёт новую строку, содержащую указанные символы из массива Data начиная с позиции start (нумерация начинается с нуля) длинной length.


public static String copyValueOf(char[] data)
Создаёт новую строку, содержащую символы из указанного массива. Изменение массива после создания строки не изменяет созданную строку.


public boolean endsWith(String suffix)
Проверяет, заканчивается ли строка символами suffix.


    String str1 = "Суккот";

    if(str1.endsWith("кот"))
        System.out.println("Слово заканчивается на котике");
    else
        System.out.println("Плохое слово. Нет смысла его использовать");

public boolean equals (Object string)
Сравнивает указанный объект и строку и возвращает true, если сравниваемые строки равны, т.е. содержит те же символы и в том же порядке с учётом регистра.


    String str1 = "Кот";
    String str2 = "Кошка";

    if(str1.equals(str2))
        System.out.println("Строки совпадают");
    else
        System.out.println("Строки не совпадают");    
Не путать метод с оператором ==, который сравнивает две ссылки на объекты и определяет, ссылаются ли они на один и тот же экземпляр. Смотри пункт Сравнение строк: equals() или ==?

public boolean equalsIgnoreCase(String string)
Сравнивает указанную строку с исходной строкой без учёта регистра и возвращает true, если они равны. Диапазон A-Z считается равным диапазону a-z.


    String str1 = "Кот";
    String str2 = "кот";

    if(str1.equalsIgnoreCase(str2))
        System.out.println("Строки совпадают");
    else
        System.out.println("Строки не совпадают");

public static String format(Locale locale, String format, Object... args)
Возвращает отформатированную строку, используя прилагаемый формат и аргументы, локализованных в данной области. Например дату или время


    // выводим число типа float с двумя знаками после запятой
    String.format("%.2f", floatValue);
Склеиваем два слова, которые выводятся с новой строки. При этом второе слово выводится в верхнем регистре.


    String str1 = "Кот";
    String str2 = "васька";
    String strResult = String.format("%s\n%S", str1, str2);
    // выводим результат в TextView
    System.out.println(strResult);

Конвертируем число в восьмеричную систему.

    String str1 = "8";
    int inInt = Integer.parseInt(str1); // конвертируем строку в число
    String strResult = String.format("(Восьмеричное значение): %o\n", inInt);

    System.out.println(strResult);
По аналогии выводим в шестнадцатеричной системе


    String str1 = "255";
    int inInt = Integer.parseInt(str1);
    String strResult = String.format("(Шестнадцатеричное значение): %x\n", inInt);
    // число 255 будет выведено как ff
    System.out.println(strResult);

Для верхнего регистра используйте %X, тогда вместо ff будет FF.

Для десятичной системы используйте %d.

Дату тоже можно выводить по разному.

    Date now = new Date();
    Locale locale = Locale.getDefault();
    System.out.println(
            String.format(locale, "%tD\n", now) + // (MM/DD/YY)
            String.format(locale, "%tF\n", now) + // (YYYY-MM-DD)
            String.format(locale, "%tr\n", now) + // Full 12-hour time
            String.format(locale, "%tz\n", now) + // Time zone GMT offset
            String.format(locale, "%tZ\n", now)); // Localized time zone bbreviation


public byte[] getBytes(String charsetName)
Возвращает отформатированную строку, используя прилагаемый формат.

public void getBytes(int start, int end, byte[] data, int index) и другие перегруженные версии
Метод сохраняет символы в массив байтов, альтернатива методу getChars(). Часто используется при экспорте строк из различных источников, где используются другие символы Unicode. Например, Java по умолчанию работает с 16-битовые символы Unicode, а в интернете часто строки используют 8-битовый код Unicode, ASCII и др.



public void getChars(int start, int end, char[] buffer, int index)
Метод для извлечения нескольких символов из строки. Вам надо указать индекс начала подстроки (start), индекс символа, следующего за концом извлекаемой подстроки (end). Массив, который принимает выделенные символы находится в параметре buffer. Индекс в массиве, начиная с которого будет записываться подстрока, передаётся в параметре index. Следите, чтобы массив был достаточного размера, чтобы в нём поместились все символы указанной подстроки.


    String unusualCat = "Котёнок по имени Гав";
    int start = 5;
    int end = 12;
    char[] buf = new char[end - start];
    unusualCat.getChars(start, end, buf, 0);
    System.out.println(new String(buf));

public int hashCode()
Возвращает целое число — хэш-код для данного объекта.


public int indexOf(int c)
Возвращает номер первой встречной позиции с указанным индексом с.

    String testString = "котёнок";
    // символ ё встречается в четвёртой позиции (index = 3)
    System.out.println(String.valueOf(testString.indexOf("ё")));

public int indexOf (int c, int start)
Ищет индекс с, начиная с позиции start

    String testString = "котёнок";
    // вернёт -1, так как после 5 символа буквы ё нет
    System.out.println(String.valueOf(testString.indexOf("ё", 4)));

public int indexOf (String string)
Ищет цепочку символов subString

String testString = "У окошка";
System.out.println(String.valueOf(testString.indexOf("кошка")));

public int indexOf (String subString, int start)
Ищет цепочку символов subString, начиная с позиции start

String testString = "У окошка";
System.out.println(String.valueOf(testString.indexOf("кошка", 2)));

public String intern ()
«Xэширует» строку


public boolean isEmpty ()
Проверяет, является ли строка пустой


    if(catname.isEmpty()) {
        // здесь ваш код
    }
Данный метод появился в API 9 (Android 2.1). Для старых устройств используйте String.length() == 0

public int lastIndexOf (String string) и другие перегруженные версии
Возвращает номер последней встречной позиции с указанным индексом. Например, получить имя файла без расширения можно так:


filename.toString().substring(0, filename.getString().lastIndexOf("."));
В этом примере мы получаем позицию последней точки и получаем подстроку до неё.

public int length()
Возвращает длину строки

    String testString = "котёнок";

    System.out.println(String.valueOf(testString.length())); // возвращает 7 (семь символов)

    public boolean matches(String regularExpression)
Проверяет, соответствует ли строка регулярным выражениям.



public int offsetByCodePoints (int index, int codePointOffset)
Возвращает позицию, расположенную на расстоянии codePointOffset после начальной позиции, заданной параметром index


public boolean regionMatches (int thisStart, String string, int start, int length)
Метод сравнивает указанную часть строки с другой частью строки. Нужно задать индекс начала диапазон строки вызывающего объекта класса String. Строка для сравнивания передаётся в параметре string. Индекс символа, начиная с которого нужно выполнять сравнение передаётся в параметре start, а длина сравниваемой подстроки в параметре length.



public boolean regionMatches (boolean ignoreCase, int thisStart, String string, int start, int length)
Перегруженная версия. Метод сравнивает указанную часть строки с другой частью строки, игнорируя регистр.



public String replace(CharSequence target, CharSequence replacement) и другие перегруженные версии
Меняет символ или последовательность символов target на replacement


    String testString = "кит";
    // меняем и на о
    System.out.println(testString.replace("и", "о")); // возвращается кот


public String replaceAll (String regularExpression, String replacement)


public String replaceFirst (String regularExpression, String replacement)
Удаляет первые символы при помощи регулярного выражения.

Например, если нужно удалить нули в начале чисел 001, 007, 000024, то можно использовать такой вызов.


    String s = "001234-cat";
    String s = s.replaceFirst ("^0*", ""); // останется 1234-cat

public String[] split (String regularExpression) и другие перегруженные версии
Разбивает строку на массив из слов. Например, есть строка Васька Рыжик Мурзик Барсик и мы хотим получить массив имён котов:


    String catnames = "Васька Рыжик Мурзик Барсик";  
    String aCats[] = catnames.split(" ");  // по пробелу
    Получим:

    aCats[0] = Васька
    aCats[1] = Рыжик
    aCats[2] = Мурзик
    aCats[3] = Барсик

public boolean startsWith(String prefix)
Проверяет, начинается ли строка символами prefix с начала строки


    String str1 = "котлета";

    if(str1.startsWith("кот"))
        System.out.println("Слово содержит кота");
    else
        System.out.println("Плохое слово. Нет смысла его использовать");

public boolean startsWith(String prefix, int start)
Проверяет, начинается ли заданная строка символами prefix с указанной позиции.


    String str1 = "Суккот";

    if(str1.startsWith("кот", 3))
        System.out.println("Слово содержит кота");
    else
        System.out.println("Плохое слово. Нет смысла его использовать");

public CharSequence subSequence (int start, int end)
Аналогичен методу substring(), но может использоваться для CharSequence.


public String substring(int start) и другие перегруженные версии.
Создаёт новую последовательность/строку с символами из данной строки начиная с позиции start до конца строки/заканчивая символом с позиции end. Новая строка содержит символы от start до end - 1, поэтому берём на один символ больше.


    String testString = "скотина";

    System.out.println(testString.substring(1, 4)); // возвращается кот


public char[] toCharArray()
Копирует символы в этой строке в массив символов. Тот же результат можно получить через метод getChars(). Документация не рекомендует использовать данный метод, предлагая метод charAt().


    String unusualCat = "Котёнок по имени Гав";

    char[] yomoe = unusualCat.toCharArray();
    System.out.println(String.valueOf(yomoe[3]));

public String toLowerCase() и другие перегруженные версии
Преобразовывает строку в нижний регистр. Преобразованием управляет заданный по умолчанию региональный язык.


    String cat = "Кот";
    String lower = cat.toLowerCase();
    System.out.println(lower);

public String toString ()
Возвращает строку. Для самой строки, которая сама уже является строкой, возвращать строку бессмысленно (о, как я загнул). Но на самом деле этот метод очень полезен для других классов.


public String toUpperCase()
Преобразовывает строку в верхний регистр. Преобразованием управляет заданный по умолчанию региональный язык.


    String cat = "Кот";
    String upper = cat.toUpperCase();
    System.out.println(upper);

public String trim()
Удаляет пробелы в начале и в конце строки.


    String str = "   Hello Kitty  ".trim();
    System.out.println(str);

public static String valueOf(long value) и другие перегруженные версии
Конвертирует содержимое (числа, объекты, символы, массивы символов) в строку.


    int catAge = 7; // это число

    System.out.println(String.valueOf(catAge)); // преобразовано в строку
Генерируем случайную строку
Допустим, нам нужна случайная строка из заданных символов.


    private static final String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int STR_LENGTH = 9; // длина генерируемой строки
    Random random = new Random();
        
    public void onClick(View view) {
        TextView infoTextView = (TextView) findViewById(R.id.textViewInfo);
        infoTextView.setText(createRandomString());
    }

    public String createRandomString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < STR_LENGTH; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }


Сравнение строк: equals() или ==?
---------------------------------

    String str1 = "Мурзик";
    String str2 = new String(str1);
    boolean isCat = str1 == str2;

    System.out.println(str1 + " == " + str2 + " -> " + isCat);
Хотя в двух переменных содержится одно и то же слово, мы имеем дело с двумя разными объектами и оператор == вернёт false.

Строка в Java - это отдельный объект, который может не совпадать с другим объектом, хотя на экране результат выводимой строки может выглядеть одинаково. Просто Java в случае с логическим оператором == (а также !=) сравнивает ссылки на объекты (при работе с примитивами такой проблемы нет):


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


Преобразование
--------------
1. Число в строку

    int integerVariable = 10;
    String first = integerVariable + ""; // конкатенация с пустой строкой
    String second = String.valueOf(integerVariable); // вызов статического метода valueOf класса String
    String third = Integer.toString(integerVariable); // вызов метода toString класса-обертки

2. Строку в число

    String string = "10";
    int first = Integer.parseInt(string); 
    /* 
       получаем примитивный тип (primitive type) 
       используя метод parseXхх нужного класса-обертки,
       где Xxx - имя примитива с заглавной буквы (например parseInt) 
    */
    int second = Integer.valueOf(string); // получаем объект wrapper класса и автоматически распаковываем


StringBuffer
============
Строки являются неизменными, поэтому частая их модификация приводит к созданию новых объектов, что в свою очередь расходует драгоценную память. Для решения этой проблемы был создан класс java.lang.StringBuffer, который позволяет более эффективно работать над модификацией строки. Класс является mutable, то есть изменяемым — используйте его, если Вы хотите изменять содержимое строки. StringBuffer может быть использован в многопоточных средах, так как все необходимые методы являются синхронизированными.

Создание
--------
Существует четыре способа создания объекта класса StringBuffer. Каждый объект имеет свою вместимость (capacity), что отвечает за длину внутреннего буфера. Если длина строки, что хранится в внутреннем буфере, не превышает размер этого буфера (capacity), то нет необходимости выделять новый массив буфера. Если же буфер переполняется — он автоматически становиться больше.

    StringBuffer firstBuffer = new StringBuffer(); // capacity = 16
    StringBuffer secondBuffer = new StringBuffer("ababahalamaga"); // capacity = str.length() + 16
    StringBuffer thirdBuffer = new StringBuffer(secondBuffer); // параметр - любой класс, что реализует CharSequence
    StringBuffer fourthBuffer = new StringBuffer(50); // передаем capacity

Модификация
-----------
В большинстве случаев мы используем StringBuffer для многократного выполнения операций добавления (append), вставки (insert) и удаления (delete) подстрок. Тут все очень просто, например:

    String domain = ".ua";
    // создадим буфер с помощью String объекта
    StringBuffer buffer = new StringBuffer("ababahalamaga"); // "ababahalamaga"
    // вставим домен в конец
    buffer.append(domain); // "ababahalamaga.ua"
    // удалим домен
    buffer.delete(buffer.length() - domain.length(), buffer.length()); // "ababahalamaga"
    // вставим домен в конец на этот раз используя insert
    buffer.insert(buffer.length(), domain); // "ababahalamaga.ua"


Рассмотрим пример класса точек на плоскости:

    class Point {
        public double x; // абсцисса точки
        public double y; // ордината точки

        // возвращает строку с описанием точки
        public String toString() {
            return "("+x+";"+y+")";
        }
        // выводит на экран описание точки
        public void printPoint() {
            System.out.print(this.toString());
        } 
        // метод перемещает точку на указанный вектор
        public void movePoint(double a, double b) {
            x = x + a;
            y = y + b;
        }
        // метод изменяет координаты точки на указанные
        public void setPoint(double a, double b) {
            x = a;
            y = b;
        } 
        // конструктор по умолчанию, создающий точку в начале координат
        public Point() {
            x = 0.0;
            y = 0.0;
        }
        // конструктор, создающий точку с указанными координатами
        public Point(double a, double b) {
            x = a;
            y = b;
        }  
        // метод вычисляющий расстояние между точками
        public double length(Point p) {
            return Math.sqrt( Math.pow(p.x-x,2) + Math.pow(p.y-y,2) );
        }
        // метод проверяющий совпадают ли точки
        public boolean equalsPoint(Point p) {
            if(this.x == p.x && this.y == p.y) {
                return true;
            } else {
                return false;
            }
        }    
    }

    public class Main {
        public static void main(String[] args) {
            Point p1 = new Point();
            Point p2 = new Point(1,1);
            System.out.println("Растстояние между точками "+p1+" и "+p2+" равно "+p1.length(p2));
        }
    }

В этом классе создаётся отдельный метод toString(), предназначенный для представления каждого объекта в виде строки. Этот метод можно использовать для собственных нужд (например, он вызывается в методе printPoint(), печатающем строку на экран), но кроме этого метод toString() будет вызываться автоматически, когда возникнет попытка автоприведения объекта к строковому типу.

Например, мы можем пользоваться методом явно:

    Point p3 = new Point(3,4.67);
    System.out.println("Координаты точки: "+p3.toString());
А можем просто обединять наш объект с другой строкой, провоцируя автоприведение:

    Point p4 = new Point(3.2,2.3);
    System.out.println("Координаты точки: "+p4);
Результат при этом будем получать идентичный.

Ещё один яркий пример метода, наследуемого от коренного класса Object — это метод equals(Object o) для сравнения объектов — его можно применять к любым двум объектам (даже если они из разных классов), вызывая метод для одного из них, а второй передавая через параметр. Метод будет возвращать истинное значение тогда и только тогда, когда будет вызван для двух ссылок на один и тот же объект. В своих программах с практической точки зрения равными можно считать разные объекты имеющие одинаковый набор текущих значений в полях, поэтому метод equals обычно тоже перегружают. Вместе с ним обязательно перегружать и метод hashCode(), возвращающий некоторое целое число для каждого объекта, являющегося его уникальным числовым идентификатором (хешем). По умолчанию это число строится на основании адреса объекта в памяти, но при перегрузке метода можно придумать свою собственную реализацию, главное, чтобы она удовлетворяла одному важному правилу: если два обекта совпадают в соответствии с методом equals, то у них должны быть одинаковые хеши, возвращаемые методом hashCode(), при этом обратного не требуется. Например, для нашего класса Point мы могли бы в качестве хеша возвращать произведение координат точки.


Полями создаваемого класса могут быть не только переменные встроенных типов, но и объекты любых других доступных классов.

Соответственно, удобно использовать ранее созданные классы в новых. При этом задействовав методы существующих классов можно значительно упростить создание нового класса.

Рассмотрим пример, в котором класс окружностей создаётся с использованием класса точек (одним из полей класса окружностей является объект-точка):

    import java.util.Scanner;

    class Point {
        public double x; // абсцисса точки
        public double y; // ордината точки

        // возвращает строку с описанием точки
        public String toString() {
            return "("+x+";"+y+")";
        }
        // выводит на экран описание точки
        public void print() {
            System.out.print(this.toString());
        }
        // метод перемещает точку на указанный вектор
        public void move(double a, double b) {
            x = x + a;
            y = y + b;
        }
        // метод изменяет координаты точки на указанные
        public void set(double a, double b) {
            x = a;
            y = b;
        }
        // конструктор по умолчанию, создающий точку с указанными пользователем координатами
        public Point() {
            boolean err;
            do {
                err = false;
                System.out.print("Введите абсциссу точки: ");
                Scanner scan = new Scanner(System.in);
                if(scan.hasNextDouble()) {
                    x = scan.nextDouble();
                } else {
                    System.out.println("Вы ввели не число, попробуйте снова");
                    err = true;
                }
            } while (err);
            do {
                err = false;
                Scanner scan = new Scanner(System.in);
                System.out.print("Введите ординату точки: ");
                if(scan.hasNextDouble()) {
                    y = scan.nextDouble();
                } else {
                    System.out.println("Вы ввели не число, попробуйте снова");
                    err = true;
                }
            } while (err);        
        }
        // конструктор, создающий точку с указанными координатами
        public Point(double a, double b) {
            x = a;
            y = b;
        }  
        // метод вычисляющий расстояние между точками
        public double length(Point p) {
            return Math.sqrt( Math.pow(p.x-x,2) + Math.pow(p.y-y,2) );
        }
        // метод проверяющий совпадают ли точки
        public boolean equalsPoint(Point p) {
            if(this.x == p.x && this.y == p.y) {
                return true;
            } else {
                return false;
            }
        }   
    }

    class Circle {
        public double r; // радиус
        public Point c; // центр
        
        // возвращает строку с описанием окружности
        public String toString() {
            return "Окружность с центром в точке " + c + " и радиусом " + r;
        }  
        // выводит на экран описание окружности
        public void print() {
            System.out.print(this.toString());
        }    
        // метод перемещает центр окружности на указанный вектор
        public void move(double a, double b) {
            c.move(a, b);
        }
        // метод изменяет окружность, перемещая центр в указанные координаты и меняя радиус
        public void set(double a, double b, double m) {
            c.set(a, b);
            r = m;
        }    
        // метод изменяет окружность, перемещая центр в указанную точку и меняя радиус
        public void set(Point p, double m) {
            c.set(p.x, p.y);
            r = m;
        }   
        // конструктор по умолчанию, создающий окружность с указанными пользователем параметрами
        Circle () {
            System.out.println("Задайте центр окружности:");
            c = new Point();
            boolean err;
            do {
                err = false;
                Scanner scan = new Scanner(System.in);
                System.out.print("Задайте радиус: ");
                if(scan.hasNextDouble()) {
                    r = scan.nextDouble();
                    if (r <= 0) {
                       System.out.println("Радиус окружности должен быть положительным");
                       err = true;
                    }
                } else {
                    System.out.println("Вы ввели не число, попробуйте снова");
                    err = true;
                }
            } while (err);        
        }
        Circle (double a, double b, double m) {
            c.set(a, b);
            r = m;
        }      
        // метод вычисляющий длину окружности
        public double length(Point p) {
            return 2*Math.PI*r;
        }
        // метод проверяющий, совпадают ли две окружности
        public boolean equalsCircle(Circle o) {
            if(this.r == o.r && c.equalsPoint(o.c)) {
                return true;
            } else {
                return false;
            }
        }      
    }

    public class Main {
        public static void main(String[] args) {
            Circle o1 = new Circle();
            o1.print();
        }
    }

в методах класса окружностей мы использовали методы класса точек, что значительно упростило построение второго класса.

Задачи

Создайте в классе метод, который будет выводить на экран сообщение о том, в какой координатной четверти лежит точка.
Создайте в классе метод, проверяющий, являются ли две точки симметричными относительно начала отсчёта.
Измените в классе конструктор по умолчанию таким образом, чтобы начальные координаты точки при её создании пользователь задавал с клавиатуры.
Создайте в классе метод, проверяющий, являются ли три точки коллинеарными (т.е. лежащими на одной прямой).
Вместо представленного метода equalsPoint перегрузите в классе методы equals и hashCode.
