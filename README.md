# java Unit 07

        // Имя пакета задаётся при создании проекта.
        package vehicle;

        class Car {
            private String brand; // The make of the car as a string
            private String model; // The model of the car as a string.
            private int year; // The integral year the car was built.
            private int gear; // передача
            private int wheels; // колесо - An integer representing the number of wheels the car has.
            private int numberOfSeat;
            
            private int miles; // The integral number of miles driven on the car.
            private Date sold_on; // Дата - транспортное средство было продано

            public Car() {
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public int getYearWasBuilt() {
                return year;
            }

            public void setYearWasBuilt(int year) {
                this.year = year;
            }

            public int getNumberOfSeat() {
                return numberOfSeat;
            }

            public void setNumberOfSeat(int numberOfSeat) {
                this.numberOfSeat = numberOfSeat;
            }

            public int getGear() {
                return gear;
            }

            public void setGear(int gear) {
                this.gear = gear;
            }

            public int getWheel() {
                return wheels;
            }

            public void setWheel(int wheels) {
                this.wheels = wheels;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }
        }


Date
====
Класс Date предназначен для работы с текущими датой и временем и позволяет отталкиваться от них для решения своих задач. При выходе новых версий Java часть методов класса была перемещена в классы Calendar и DateFormat.

При импорте выбирайте java.util.Date.

У класса есть два конструктора:

Date()
------
    Date(long milliseconds)
Первый конструктор без параметров инициализирует объект текущей датой и временем. Во втором конструкторе вы можете указать количество миллисекунд, прошедших с полуночи 1 января 1970 года.

Пример вывода даты на экран.
----------------------------

        // Создадим объект Date
        Date date = new Date();

        System.out.println(date.toString());

Методы:
-------
boolean after(Date date) - если объект класса Date содержит более позднюю дату, чем указано в параметре, то возвращается true

boolean before(Date date) - если объект класса Date содержит более раннюю дату, чем указано в параметре, то возвращается true

int compareTo(Date date) - сравнивает даты. Возвращает 0, если совпадают, отрицательное значение - если вызывающая дата более ранняя, положительное значение - если вызывающая дата более поздняя, чем в параметре

boolean equals(Object object) - если даты совпадают, то возвращается true

long getTime() - возвращает количество миллисекунд, прошедших с полуночи 1 января 1970 года

        // Создадим объект Date
        Date date = new Date();

        long millis = date.getTime();

        System.out.println(String.valueOf(millis));

после создания можно изменить время с помощью setTime(long date)

void setTime(long milliseconds) - устанавливает время и дату в виде числа миллисекунд, прошедших с полночи 1 января 1970 года.


        public class MainCar {

            public static void main(String[] args) {
                Date date = new Date();
                System.out.println(date.toString());
                long millis = date.getTime();
                System.out.println(String.valueOf(millis));
                Car car = new Car(); // Создаём объект car класса Car с помощью конструктора по умолчанию
                car.setBrand("Honda");
               
                System.out.println("Brand = " + car.getBrand());
            }

        }


существует множество методов для получения или установки отдельных компонентов времени и даты, например, getMinutes()/setMinutes() и др. Все они являются устаревшими и вместо них следует использовать класс Calendar.

класс Calendar
--------------
Для удобной работы с датой и временем в Java используются классы Calendar и Date. Оба класса находятся в библиотеке java.util. 

Для того, чтобы отображать дату и время в удобном для вас формате используется SimpleDataFormat:

        import java.text.SimpleDateFormat;
        import java.util.Date;
        public class Test {
            public void test()
            {
                Date d = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                SimpleDateFormat format2 = new SimpleDateFormat("День dd Месяц MM Год yyyy Время hh:mm");
                System.out.println(format1.format(d)); //25.02.2013 09:03
                System.out.println(format2.format(d)); //День 25 Месяц 02 Год 2013 Время 09:03
            }
        }

при создании шаблона для отображения даты dd — означает день, MM — месяц, yyyy — год, hh — часы и mm — минуты. В шаблоне могут присутствовать не все единицы, кроме того как вы увидели выше в качестве разделитель можно использовать любой текст.

Рассмотрим абстрактный класс Calendar. Он позволяет работать с датой в рамках календаря, т.е он умеет прибавлять день, при этом учитывать високосные годы и прочее. Единственной реализацией его является класс GregorianCalendar, также как и у даты конструктор по умолчанию возвращает календарь на текущий день, но вы можете задать его явно указав все параметры:

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

        public class Test {
            public void test()
            {
                Calendar c = new GregorianCalendar();//календарь на текущую дату
                Calendar c2 = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013
                c2.add(Calendar.DAY_OF_YEAR, 1); //увеличиваем дату на 1 день
                System.out.println(c2.getTime());// 26.11.2013
                c2.add(Calendar.DAY_OF_YEAR, -1); //уменьшаем дату на 1 день
            }
        }

Календарь достаточно мощный класс, который позволяет получать названия месяцев и дней недели, увеличивать или уменьшать различные параметры текущей даты, а также получать их.

        DAY_OF_YEAR — день года (0- 365)
        DAY_OF_MONTH — день месяца( какой по счету день в месяце 0 — 31)
        WEEK_OF_MONTH — неделя месяца
        WEEK_OF_YEAR — неделя в году
        MONTH — номер месяца
        Year — номер года
        Calendar.ERA — эра

большинство методов принимает на вход Int field, где в качестве одного из вариантов вы можете выбрать перечисленные выше значения.

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

        public class Test {
            public void test()
            {
                Calendar c = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013
                System.out.println(c.get(Calendar.MONTH));//11
                System.out.println(c.get(Calendar.YEAR));//2013
                System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));//4
                System.out.println(c.get(Calendar.DAY_OF_WEEK));//4
                System.out.println(c.get(Calendar.DAY_OF_YEAR));//359
                System.out.println(c.get(Calendar.DAY_OF_MONTH));//25
            }
        }

class Car
---------
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

        class Car {
            private String brand; // The make of the car as a string
            private String model; // The model of the car as a string.
            private Date built_date; // The integral year the car was built.
            private int gear; // передача
            private int wheels; // колесо - An integer representing the number of wheels the car has.
            private int numberOfSeat;
            
            private int miles; // The integral number of miles driven on the car.
            private Date sold_on; // Дата - транспортное средство было продано

            public Car() {
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public Date getDateWasBuilt() {
                return built_date;
            }

            public void setDateWasBuilt(Date built_date) {
                this.built_date = built_date;
            }

            public int getNumberOfSeat() {
                return numberOfSeat;
            }

            public void setNumberOfSeat(int numberOfSeat) {
                this.numberOfSeat = numberOfSeat;
            }

            public int getGear() {
                return gear;
            }

            public void setGear(int gear) {
                this.gear = gear;
            }

            public int getWheel() {
                return wheels;
            }

            public void setWheel(int wheels) {
                this.wheels = wheels;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }
        }

        public class MainCar {

            public static void main(String[] args) {

                Date date = new Date();
                Calendar cal1 = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013

                System.out.println(date.toString());
                
                System.out.println(cal1.getTime());// 26.11.2013

                Car car = new Car(); 
                car.setBrand("Honda");
                car.setDateWasBuilt(cal1.getTime());
                
                System.out.println("Brand = " + car.getBrand());
                System.out.println("Brand = " + car.getDateWasBuilt().toString());

            }

        }

Наследование
============
Один класс (подкласс) может наследовать переменные и методы другого класса (суперкласса), используя ключевое слово extends. Подкласс имеет доступ ко всем открытым переменным и методам родительского класса, как будто они находятся в подклассе. В то же время подкласс может иметь методы с тем же именем и сигнатурой, что и методы суперкласса. В этом случае подкласс переопределяет методы родительского класса. 

В следующем примере переопределяемый метод show() находится в двух классах Car и Truck. По принципу полиморфизма вызывается метод, наиболее близкий к текущему объекту.

/* наследование класса и переопределение метода */

        class Car {
            private String brand; // The make of the car as a string
            private String model; // The model of the car as a string.
            private Date built_date; // The integral year the car was built.
            private int gear; // передача
            private int wheels; // колесо - An integer representing the number of wheels the car has.
            private int numberOfSeat;
            
            private double price;
            
            private int miles; // The integral number of miles driven on the car.
            private Date sold_on; // Дата - транспортное средство было продано

            public Car(String model, String brand, double price) { //конструктор) 
                this.model = model;
                this.brand = brand;
                this.price = price;
                
            }
            
            public double getPrice(){
                return price;
                }
            
            public void setPrice(double price){
                this.price = price;
                }
            
             public void show(){
                    System.out.println("название: " + this.model + " " + this.brand+ ", цена: "+ this.price);
                }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

Грузовая машина
---------------
        public class Truck extends Car {
            private int loadCapacity;

            public Truck() {
            }

            public int getLoadCapacity() {
                return loadCapacity;
            }

            public void setLoadCapacity(int loadCapacity) {
                this.loadCapacity = loadCapacity;
            }
        }



        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

        class Car {
            private String brand; // The make of the car as a string
            private String model; // The model of the car as a string.
            private Date built_date; // The integral year the car was built.
            private int gear; // передача
            private int wheels; // колесо - An integer representing the number of wheels the car has.
            private int numberOfSeat;
            
            private double price;
            
            private int miles; // The integral number of miles driven on the car.
            private Date sold_on; // Дата - транспортное средство было продано

            public Car(String model, String brand, double price) { //конструктор) 
                this.model = model;
                this.brand = brand;
                this.price = price;
                
            }
            
            public double getPrice(){
                return price;
                }
            
            public void setPrice(double price){
                this.price = price;
                }
            
             public void show(){
                    System.out.println("название: " + this.model + " " + this.brand+ ", цена: "+ this.price);
                }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public Date getDateWasBuilt() {
                return built_date;
            }

            public void setDateWasBuilt(Date built_date) {
                this.built_date = built_date;
            }

            public int getNumberOfSeat() {
                return numberOfSeat;
            }

            public void setNumberOfSeat(int numberOfSeat) {
                this.numberOfSeat = numberOfSeat;
            }

            public int getGear() {
                return gear;
            }

            public void setGear(int gear) {
                this.gear = gear;
            }

            public int getWheel() {
                return wheels;
            }

            public void setWheel(int wheels) {
                this.wheels = wheels;
            }

            public int getMiles() {
                return miles;
            }

            public void setMiles(int miles) {
                this.miles = miles;
            }
        }
super
-----
super позволяет обратить­ся непосредственно к конструктору суперкласса

        class Truck extends Car {
            private int loadCapacity; // вместимость

            public Truck(String model, String brand, double price,int copasity) {
                super(model,brand, price); //вызов конструктора суперкласса
                loadCapacity = copasity;
            }

            public int getLoadCapacity() {
                return loadCapacity;
            }

            public void setLoadCapacity(int loadCapacity) {
                this.loadCapacity = loadCapacity;
            }
        }


        public class MainCar3 {

            /**
             * @param args
             */
            public static void main(String[] args) {

                Date date = new Date();
                Calendar cal1 = new GregorianCalendar(2013, 11, 25);//календарь на 25.11.2013

                System.out.println(date.toString());
                
                System.out.println(cal1.getTime());// 26.11.2013
                
                Car car1 = new Car("Honda", "Hyundai", 12000);
                car1.show(); // вызов show() класса Car
                Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
                
                car2.show(); // вызов show() класса Truck

            }

        }


Объект car1 создается при помощи вызова конструктора класса Car, и, соответственно, при вызове метода show(), вызывается версия метода из класса Car. При создании объекта car2 ссылка типа Car инициализируется объектом типа Truck. При таком способе инициализации ссылка на суперкласс получает доступ к методам, переопределенным в подклассе.

При объявлении совпадающих по сигнатуре полей в суперклассе и подклассах их значения не переопределяются и никак не пересекаются, то есть существуют в одном объекте независимо друг от друга. В этом случае задача извлечения требуемого значения определенного поля, принадлежащего классу в цепочке наследования,  ложится на программиста.


        /* доступ к полям с одинаковыми именами при наследовании */

        class Truck extends Car {
            private int loadCapacity; // вместимость
            private int wheels;

            public Truck(String model, String brand, double price,int copasity) {
                super(model,brand, price); //вызов конструктора суперкласса
                loadCapacity = copasity;
            }

            public int getWheel() {
                return wheels;
            }

            public void setWheel(int wheels) {
                this.wheels = wheels;
            }

            public int getLoadCapacity() {
                return loadCapacity;
            }

            public void setLoadCapacity(int loadCapacity) {
                this.loadCapacity = loadCapacity;
            }
        }



        public class MainCar4 {

            public static void main(String[] args) {
               
                Car car1 = new Car("Honda", "Hyundai", 12000);
                car1.show(); // вызов show() класса Car

                car1.setWheel(4);
                System.out.println(car1.getModel()+" has "+ car1.getWheel()+" wheels");
                
                Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
                
                car2.setWheel(8);
                
                car2.show(); // вызов show() класса Truck
                System.out.println(car2.getModel()+" has "+car2.getWheel()+" wheels");

            }


В результате выполнения данного кода последовательно будет выведено:

        название: Honda Hyundai, цена: 12000.0
        Honda has 4 wheels

        название: Honda Ridgeline Hyundai, цена: 20000.0
        Honda Ridgeline has 8 wheels


Нельзя создать подкласс для класса, объявленного со спецификатором final:

        // класс First не может быть суперклассом

        final class First {/*код*/}
        // следующий класс невозможен
        class Second extends First{/*код*/}

Использование super и this
--------------------------
Ключевое слово super используется для вызова конструктора суперкласса и для доступа к члену суперкласса. 

    super(список_параметров); /* вызов конструктора суперкласса с передачей параметров */

    super.i = n; /* присваивание значения атрибуту суперкласса */
    super.method(); // вызов метода суперкласса

Вторая форма super подобна ссылке this на экземпляр класса. Третья форма специфична для Java и обеспечивает вызов переопределенного метода, причем если в суперклассе этот метод не определен, то будет осуществляться поиск по цепочке наследования до тех пор, пока метод не будет найден. 

Каждый экземпляр класса имеет неявную ссылку this на себя, которая передается также и методам. После этого можно писать this.price хотя и необязательно.

Следующий код показывает, как, используя this, можно строить одни конструкторы на основе других.

    // this в конструкторе 

      public Car(String model, String brand, double price) { //конструктор) 
            this.model = model;
            this.brand = brand;
            this.price = price;
            
        }
        
        public Car() {
            this(" "," ", 0);
            }

В этом классе второй конструктор для завершения инициализации объекта обращается к первому конструктору. Такая конструкция применяется в случае, когда в классе имеется несколько конструкторов и требуется добавить конструктор по умолчанию.
Ссылка this используется в методе для  уточнения того, о каких именно  переменных идет речь в каждом отдельном случае, а конкретно для доступа к переменной класса, если в методе есть локальная переменная с тем же именем. Инструкция this должна быть единственной в вызывающем конструкторе и быть первой по счету выполняемой операцией.

Переопределение методов
-----------------------
Способность Java делать выбор метода, исходя из типа времени выполнения, называется динамическим полиморфизмом. Поиск метода происходит сначала в данном классе, затем в суперклассе, пока метод не будет найден или не  достигнут Object – суперкласс для всех классов.
Оператор instanceof действует в этой ситуации аналогичным образом. Результатом действия будет истина, если объект является объектом одного из подклассов класса, на принадлежность к которому проверяется данный объект, но не наоборот. Проверка на принадлежность объекта к классу Object всегда даст истину как результат. Результат применения этого оператора по отношению к null всегда ложь, потому что null нельзя причислить к какому-либо типу. В тоже время литерал null можно передавать в методы по ссылке на любой объектный тип и использовать в качестве возвращаемого значения.

статические методы могут быть переопределены в подклассе, но не могут быть полиморфными, так как их вызов не затрагивает объекты.
Полное имя метода включает его имя, возвращаемое значение и параметры. Если два метода с одинаковыми именами находятся в одном классе, списки параметров должны отличаться. Такие методы являются перегружаемыми (overload). Если метод подкласса совпадает с методом суперкласса (порождающего класса), то метод подкласса переопределяет (overriden) метод суперкласса. Все методы Java являются виртуальными (ключевое слово virtual как в C++ не используется). Переопределение методов является основой концепции динамического связывания, реализующей полиморфизм. Когда переопределенный метод вызывается через ссылку суперкласса, Java определяет, какую версию метода вызвать, основываясь на типе объекта, на который имеется ссылка. Таким образом, тип объекта определяет версию метода на этапе выполнения. 

реализация полиморфизма на основе динамического связывания. 
-----------------------------------------------------------
Так как суперкласс содержит методы, переопределенные подклассами, то объект суперкласса будет вызывать методы различных подклассов, в зависимости от того, на объект какого подкласса у него имеется ссылка.


        /* динамическое связывание методов */


        class Car {
            public void show(){
                    System.out.println("название: " + this.model + " " + this.brand+ ", цена: "+ this.price);
                }
        }

        class Truck extends Car {
            private int loadCapacity; // вместимость

            public void show() {
                 /* вывод copasity: переопределенный метод show() из Car */
                super.show(); // вывод значений из Car
                     System.out.println("copasity: " + loadCapacity);
                }


        public class MainCar {

            public static void main(String[] args) {

                Car car1 = new Car("Honda", "Hyundai", 12000);
                car1.show(); // вызов show() класса Car
                car1.setWheel(4);
                System.out.println(car1.getModel()+" has "+ car1.getWheel()+" wheels");
                
                Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
                
                car2.setWheel(8);
                
                car2.show(); // вызов show() класса Truck
                System.out.println(car2.getModel()+" has "+car2.getWheel()+" wheels");



Результат:
----------
        название: Honda Hyundai, цена: 12000.0
        Honda has 4 wheels
        название: Honda Ridgeline Hyundai, цена: 20000.0
        copasity: 10
        Honda Ridgeline has 8 wheels

при вызове show() обращение super всегда происходит к ближайшему суперклассу.

Перегрузка методов
------------------
Метод называется перегруженным, если существует несколько его версий с одним и тем же именем, но с различным набором параметров. Перегрузка может ограничиваться одним классом или несколькими классами, но обязательно находящимися в одной цепочке наследования. Следует отметить, что статические методы могут перегружаться нестатическими и наоборот.

При вызове перегруженных методов следует избегать ситуаций, когда компилятор будет не в состоянии выбрать тот или иной метод, как, например, в случае:

        // вызов перегруженного метода

        class Sedan extends Car {
            private int gearType;

            public Sedan(String model, String brand, double price,int geartype) {
                super(model,brand, price); //вызов конструктора суперкласса
                gearType = geartype;
            }

            public int getGearType() {
                return gearType;
            }

            public void setGearType(int gearType) {
                this.gearType = gearType;
            }
            public void show() {
             /* вывод copasity: переопределенный метод show() из Car */
            super.show(); // вывод значений из Car
                 System.out.println("Gear Type: " + gearType);
            }
        }


        public class MainCar {

            static void show1(Car obj1, Sedan obj2) {

                System.out.println("первый метод show(Car, Sedan)");
                }

            static void show(Sedan obj1, Car obj2){

                System.out.println("второй метод show(Sedan, Car)");
                }

            static void show(Object obj1, Object obj2){
                System.out.println("третий метод show(Object, Object)");
            }

            public static void main(String[] args) {

                Car car1 = new Car("Honda", "Hyundai", 12000);
                car1.show(); // вызов show() класса Car
                car1.setWheel(4);
                System.out.println(car1.getModel()+" has "+ car1.getWheel()+" wheels");
                
                Truck car2 = new Truck("Honda Ridgeline", "Hyundai", 20000, 10);
                
                car2.setWheel(8);
                
                car2.show(); // вызов show() класса Truck
                System.out.println(car2.getModel()+" has "+car2.getWheel()+" wheels");
                
                Sedan car3 = new Sedan("Accord LX", "Hyundai", 25000, 1);
                
                car3.setWheel(4);
                
                car3.show(); // вызов show() класса Truck
                System.out.println(car3.getModel()+" has "+car3.getWheel()+" wheels");
                
               Car c = new Car();
               Sedan d = new Sedan();
               Object ob= new Object();
               
               show(c,d);//1_первый метод
               show(d,c);//2_второй метод
               show(c,c);//3_третий метод
                  //show(d,d);// 4_ошибка компиляции
               show(ob, ob);//5_третий метод
               show(c,ob);//6_третий метод
               show(ob,d);//7_третий метод

            }

В первом, втором и пятом случаях передаваемые параметры в метод show() полностью совпадают с параметрами при объявлении метода. 
       show(c,d);//1_первый метод
       show(d,c);//2_второй метод

В третьем случае первый и второй методы не годятся для использования, так как одним из параметров этих методов является объект класса Sedan, а определение вызываемого метода идет вверх по цепочке наследования для параметров, поэтому в данном случае будет вызван метод с параметрами типа Object. 
       show(c,c);//3_третий метод
Аналогичная ситуация возникает в шестом и седьмом случаях. 
        show(c,ob);//6_третий метод
        show(ob,d);//7_третий метод

В четвертом случае оба первых метода show() одинаково подходят для вызова  (также как и третий), поэтому возникнет ошибка компиляции. 
          //show(d,d);// 4_ошибка компиляции

Чтобы избежать неопределенности, следует использовать явное преобразование типов, например:

        show(d,(Car)d);
          show(d,(Object)d);

Каждый из вариантов вызовет в итоге соответствующий ему метод show().

экземпляр подкласса создается с помощью new, ссылка на него передается объекту суперкласса. 
------------------------------------------------------------------------------------------
При вызове из суперкласса соответственно вызывается метод подкласса.

        // динамический вызов метода

            class A {
                 void myMethod() {/* private и protected использовать нельзя, так как метод при наследовании становится недоступным*/
             System.out.println("метод класса А");
            }

            void myMethod(int i) {
            System.out.println("метод класса А с аргументом");
            }
            }

            class B extends A {
            void myMethod(int i) {
            System.out.println("метод класса В с аргументом");
            }
                }

            public class C extends B {
            {
            System.out.println("класс C");
            }

            void myMethod() {
            System.out.println("метод класса С");
            }
            }

            public class Dispatch {
                 public static void main(String[] args) {
                     A obj1 = new B();
                     obj1.myMethod();
                     A obj2 = new C();
                     obj2.myMethod();
            obj2.myMethod(10);
                 }
            }
Вывод:
------
        метод класса А
        класс C
        метод класса С
        метод класса В с аргументом

При первом обращении вызывается метод myMethod()  из класса A, как унаследованный. При втором обращении вызывается метод myMethod()из класса C, как переопределенный. В последнем случае вызывается метод myMethod(int i) из класса B, так как вызвать метод без аргументов нельзя и осуществляется поиск соответствующего метода по дереву наследования.

Совмещение методов
------------------
Язык Java позволяет создавать несколько методов с одинаковыми именами, но с разными списками параметров. Такая техника называется совмещением методов (method overloading).
Методы, использующие совмещение имен, не обязательно должны быть конструкторами.

        class Car {
            
                public double salePrice(){
                if (sold != false){
                    return 0.0; // Already sold
                }
                else {
                return this.getWheel()*5000.0;
                }
              }
           public double salePrice(double amt){
            if (sold != false){
                return 0.0; // Already sold
            }
            else {
            return this.getWheel()*amt;
            }
          }

        }

    
    public static void main(String[] args) {

                
        System.out.println("Sale Price for "+car1.getModel()+" is "+car1.salePrice()+" $");
        
        System.out.println("Sale Price for "+car2.getModel()+" is "+car2.salePrice(6000.0)+" $");
        }


Замещение методов
-----------------
Новый подкласс Sedan класса Car наследует реализацию метода salePrice своего суперкласса. Проблема заключается в том, что в классе Car уже определена версия метода salePrice(), которая возвращает sale price. Мы должны заместить (override) это определение метода новым, пригодным для случая Sedan. 
        
        class Car {
            protected boolean sold = false;
        }

        class Sedan {
         public double salePrice(double amt){
               if (sold != false){
                     return 0.0; // Already sold
                 }
               else {
                switch (this.getGearType()) {
                    case 1:
                    return this.getWheel()*amt*1.2;
                    
                    case 2:
                    return this.getWheel()*amt*1.5;
                    
                    default:
                    return this.getWheel()*amt;
                    }
                
                }
              }
        }


        Sedan car3 = new Sedan("Accord LX", "Hyundai", 25000, 1);
        
        car3.setWheel(4);
        System.out.println("Sale Price for "+car3.getModel()+" is "+car3.salePrice(6000.0)+" $");
        car3.show(); // вызов show() класса Truck
        System.out.println(car3.getModel()+" has "+car3.getWheel()+" wheels");

Динамическое назначение методов
-------------------------------
Давайте в качестве примера рассмотрим два класса, у которых имеют простое родство подкласс / суперкласс, причем единственный метод суперкласса замещен в подклассе.                     

 
        Car car4  = new Sedan("Accord LX2", "Hyundai", 35000, 2);
        car4.setWheel(4);
        System.out.println("Sale Price for "+car4.getModel()+" is "+car4.salePrice(6000.0)+" $");

внутри метода main мы объявили переменную car4 класса Car, а проинициализировали ее ссылкой на объект класса Sedan. В следующей строке мы вызвали метод salePrice. При этом транслятор про­верил наличие метода callme у класса Car, а исполняющая система, уви­дев, что на самом деле в переменной хранится представитель класса Sedan, вызвала не метод класса Car, а callme класса Sedan.

Полимофизм и расширяемость
--------------------------
В следующем примере приведение к базовому типу происходит в  выражении:
Stone s1 = new White();
Stone s2 = new Black();
Базовый класс Stone предоставляет общий интерфейс для всех наследников. Порожденные классы перекрывают эти определения для обеспечения уникального поведения.

        // полиморфизм 

        class Stone {
           public void add() {/*пустая реализация*/}
        }
        class White extends Stone {
          public void add() {
        System.out.println("добавлен белый камень");
        }
         
        }

        class Black extends Stone {
         public void add() {
        System.out.println("добавлен черный камень ");
        }
        }

        public class StoneRandom {
           public static Stone randStone() {
        //if((int)(Math.random() * 2)==0) return new Black();

        //else return new White();//как альтернативный вариант
           switch((int)(Math.random() * 2)){
             case 0:  return new Black();
             case 1:  return new White();
        default: return null;
            }
        }

        public static void main(String[] args) {
           Stone[] s = new Stone[10];
          for(int i = 0; i < s.length; i++)
        /* заполнение массива камнями */
             s[i] = randStone();
          for(int i = 0; i < s.length; i++)
             s[i].add();// вызов полиморфного метода
             }
        }

Главный класс StoneRandom содержит static метод randStone(), который возвращает ссылку на случайно выбранный  объект подкласса класса Stone каждый раз, когда он вызывается. Приведение к базовому типу производится оператором  return, который возвращает ссылку на Black или White. Метод main() содержит массив из ссылок Stone, заполненный вызовами randStone(). На этом этапе известно, что имеется некоторое множество ссылок на объекты базового типа и ничего больше (не больше, чем знает компилятор). Kогда происходит перемещение по этому массиву, метод add() вызывается для каждого случайным образом выбранного объекта.
Если понадобится в дальнейшем добавить систему, например класс Green, то это потребует только переопределения соответствующих методов и добавления одной строки в код метода randStone(), что делает систему легко расширяемой.

Статические методы и полиморфизм
--------------------------------
К статическим методам принципы полиморфизма неприменимы. При использовании для доступа к статическому члену ссылки, компилятор, при выборе метода или поля, учитывает тип ссылки, а не тип объекта, ей присвоенного.

        /* поведение статического метода */

        class StaticA {
             public static void show(){
                 System.out.println("метод show() из StaticA");
             }
        }
        class StaticB extends StaticA {}
        class StaticC extends StaticB {
        public static void show(){
                 System.out.println("метод show() из StaticC");
             }
        }
        public class StaticDemo {
             public static void main(String[] args) {
                 StaticA s1 = new StaticC();
                 StaticB s2 = new StaticC();
                 StaticC s3 = new StaticC();
                 s1.show();
                 s2.show();
                 s3.show();
             }
        }
В результате выполнения данного кода будет выведено
        метод show() из StaticA
        метод show() из StaticA
        метод show() из StaticC

При таком способе инициализации объектов s1 и s2, метод show() будет вызван из суперклассов StaticA и StaticB соответственно. Полиморфизм наблюдается только в смысле наследования методов. Для объекта s3 будет вызван собственный метод show(), что следует из способа объявления объекта. Если же спецификатор static убрать из объявления методов, то вызовы методов будут осуществляться в соответствии с принципами полиморфизма.

Сборка «мусора»
----------------
Так как объекты создаются динамически с помощью операции new, то желательно знать механизм ликвидации объектов и способ освобождения памяти для более позднего перераспределения. Java автоматически выполняет освобождение памяти, занимаемой объектом, с помощью механизма «сборки мусора». Когда никаких ссылок на объект не существует, то есть ссылка на него вышла из области видимости программы, предполагается, что объект больше не нужен, и память, занятая объектом, может быть освобождена. «Сборка мусора» происходит нерегулярно во время выполнения программы. Форсировать «сборку мусора» невозможно, можно лишь «рекомендовать» ее выполнить вызовом метода gc(), но виртуальная машина выполнить ее тогда, когда сама почитает это удобным.
Иногда объекту нужно выполнять некоторые действия перед освобождением памяти. Например, освободить внешние ресурсы. Для обработки таких ситуаций используется механизм finalization. Чтобы использовать finalization, необходимо определить метод finalize(). Виртуальная машина вызывает этот метод всегда, когда она собирается уничтожить объект данного класса. Внутри метода finalize() нужно определить действия, которые должны быть выполнены до уничтожения объекта. Непосредственно перед освобождением памяти для объекта вызывается метод finalize().

Метод finalize() имеет следующий вид:

        protected void finalize(){
            // код завершения
        }

Ключевое слово protected запрещает доступ к finalize() кодам, определённым вне этого класса. Метод finalize() вызывается только перед самой сборкой «мусора», а не когда объект выходит из области действия идентификаторов, то есть невозможно определить, когда finalize() будет выполнен. Это метод может быть вообще не выполнен.

        /* демонстрация сборки мусора */
        class Demo {
             private int a;
             public Demo(int a) {
                 this.a = a;
             }
             protected void finalize() {
                 System.out.println("объект удален, a=" + a);
             }
        }

        public class FinalizeDemo {
             public static void main(String[] args) {
                 Demo d1 = new Demo(1);
                 d1 = null;
                 Demo d2 = new Demo(2);
                 Object d3 = d2;         //1
                 //Object d3 = new Demo(3); //2
                 d2 = d1;
                 System.gc();//просьба выполнить «сборку мусора»
             }
        }

В результате выполнения этого кода перед вызовом метода gc() без ссылки останется только один объект.

        объект удален,  a=1

Если закомментировать строку 1 и снять комментарий со строки 2, то перед выполнением gc() ссылку потеряют уже два объекта.
        объект удален,  a=1
        объект удален,  a=2
 
final
-----
Все методы и переменные объектов могут быть замещены по умолча­нию. Если же вы хотите объявить, что подклассы не имеют права за­мещать какие-либо переменные и методы вашего класса, вам нужно объ­явить их как final. 

final int FILE_NEW = 1;

По общепринятому соглашению при выборе имен переменных типа final — используются только символы верхнего регистра (т.е. используются как аналог препроцерных констант C++). Использование final-методов порой приводит к выигрышу в скорости выполнения кода — поскольку они не могут быть замещены, транслятору ничто не мешает заменять их вызовы встроенным (in-line) кодом (байт-код копируется непосредственно в код вызывающего метода).


finalize
---------
В Java существует возможность объявлять методы с именем finalize. Методы finalize аналогичны деструкторам в C++ (ключевой знак ~). Исполняющая среда Java будет вызывать его каждый раз, когда сборщик мусора соберется уничтожить объект этого класса. 

Задачи

Создайте в классе Truck метод, который будет перегружать метод salePrice класса Car.

Создайте в классе Truck метод, который будет замещать метод salePrice класса Car.

Создайте процедуру интерактивного создания записи о продаваемых машинах и проверки, продана ли машина. 




