# java Unit 11

JTable комопнент 
================
В Java Swing для отображения табличных данных используется комопнент JTable. JTable внутри себя не содержит данные, а служит только для их отображения.

    import javax.swing.JTable;

Рассмотрим простой пример создания и использования таблицы. Для этого мы создадим таблицу, заполним её тестовыми данными и разместим на панели прокрутки. 

Для начала определим, какие стобцы мы хотим показывать в таблице. 

Собираем столбцы в массив строк вот так:

        //Массив содержащий заголоки таблицы
        Object[] headers = { "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };


class JTableExample            
-------------------             
            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import javax.swing.JFrame;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;
            import javax.swing.SwingUtilities;
             
            public class JTableExample {
             
                  //Массив содержащий заголоки таблицы
                Object[] headers = { "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };
    
    // Далее необходимо определить данные, которые мы хотим отображать в таблице.
    // данные инициализируются и хранятся в двумерном Объектном массиве:
        
                //Массив содержащий информацию для таблицы
                Object[][] data = {
                    { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
                    { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    
                };
             
                //Объект таблицы
                JTable jTabCars;
    
    // Далее необходимо создать сам JTable и передать ему массив столбцов и массив с данным для показа.             
                JTableExample() {
                    
                    //Создаем новый контейнер JFrame
                    JFrame jfrm = new JFrame("JTableCars");
                    
                    //Устанавливаем диспетчер компоновки
                    jfrm.getContentPane().setLayout(new FlowLayout());
                    
                    //Устанавливаем размер окна
                    jfrm.setSize(1000, 200);
                    
                    //Устанавливаем завершение программы при закрытии окна
                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    //Создаем новую таблицу на основе двумерного массива данных и заголовков
                    jTabCars = new JTable(data, headers);
                    
                    //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                    JScrollPane jscrlp = new JScrollPane(jTabCars);
                    
                    //Устаналиваем размеры прокручиваемой области
                    jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                    
                    //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                    jfrm.getContentPane().add(jscrlp);
                    
                    //Отображаем контейнер
                    jfrm.setVisible(true);
                }
           

Одиночный поток
----------------
Swing API целиком спроектирован для работы в одном потоке. Это значит, что, когда этот одиночный поток блокируется или замедляется, весь GUI будет работать медленнее или вообще перестанет отвечать на действия пользователя. Для того чтобы этого избежать, приложение должно выполнять большинство своей работы в отдельном потоке и взаимодействовать только с потоком событий, когда тот занимается обработкой какого-либо события или когда есть необходимость обновить (или перерисовать) GUI. К счастью, большая часть Swing API уже спроектирована для такого многопоточного подхода.              

Множественные потоки
--------------------
С ростом сложности приложений они неизбежно требуют более гладкой работы множества потоков. Но это прямое противоречие дизайну Swing API. Чтобы обойти это несоответствие, компания Sun определила несколько методов в AWT и Swing API, чтобы дать разработчикам возможность использовать множественные потоки в этой однопоточной модели, которая стоит в основе дизайна Swing API. Основная идея заключается в том, чтобы разместить все инструкции, касающиеся GUI, в потоке событий. Чтобы сделать это, необходимо поставить их в очередь событий. 

Следующие два метода доступны как статические методы классов javax.swing.SwingUtilities и java.awt.EventQueue:

. invokeLater(Runnable r) вызывает метод r.run() для выполнения событий AWT асинхронно с потоком. Этот поток будет обработка, как только он достигнет вершины очереди событий.              

. invokeAndWait(Runnable r) заставляет вызывать метод r.run() одновременно с потоком выполнения событий AWT.

                //Функция main, запускающаяся при старте приложения
                public static void main(String[] args) {
                    //Создаем фрейм в потоке обработки событий
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new JTableExample();
                        }
                    });
                }
            }

@Override
---------
@Override указывает, что далее мы собираемся переопределять метод базового класса.
При этом, если в базовом классе не окажется метода с аналогичной сигнатурой, то мы получим предупреждение компилятора о том, что хотя мы и собирались что то переопределить, по факту этого не произошло.

На этом действие аннотации заканчивается.

Таким образом, аннотация никак не влияет на сам факт переопределения метода - при совпадении сигнатур с методом базового класса он и так будет переопределен, независимо от наличия, либо отсутствия этой аннотации. Аннотация служит лишь для контроля успешности действия при сборке проекта.

использование массивов для задания значений неудобно. 

создание своей модели таблицы. 
-----------------------------
Модель таблицы можно создать разными способами. Можно пойти более сложным путем, создав класс, реализующий интерфейс TableModel. Тогда придется описывать все необходимые методы интерфейса и разбираться, как они работают. Кроме того, нужно будет хранить слушатели событий для модели данных. 

Класс AbstractTableModel
------------------------
Класс AbstractTableModel содержит реализацию всех методов интерфейса TableModel за исключением getValueAt(), getRowCount() и getColumnCount(). Создадим свой класс, унаследовав его от AbstractTableModel и реализуем эти методы.

            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import javax.swing.JFrame;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;
            import javax.swing.SwingUtilities;
            import javax.swing.table.AbstractTableModel;

            class MyTableModel extends AbstractTableModel{
                @Override
                public int getRowCount() {
                    return 10;
                }
                @Override
                public int getColumnCount() {
                    return 10;
                }
                @Override
                public Object getValueAt(int r, int c) {
                    return r * c;
                }
            }
            public class MainJtab1 {

                /**
                 * @param args
                 */
                //Массив содержащий заголоки таблицы
                Object[] headers = { "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };
                 
                //Массив содержащий информацию для таблицы
                Object[][] data = {
                    { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
                    { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    
                };
             
                //Объект таблицы
                JTable jTabCars;
             
                MainJtab1() {
                    //Создаем новый контейнер JFrame
                    JFrame jfrm = new JFrame("JTableCars");
                    //Устанавливаем диспетчер компоновки
                    jfrm.getContentPane().setLayout(new FlowLayout());
                    //Устанавливаем размер окна
                    jfrm.setSize(1000, 200);
                    //Устанавливаем завершение программы при закрытии окна
                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //Создаем новую таблицу на основе двумерного массива данных и заголовков
                    // jTabCars = new JTable(data, headers);
                    jTabCars = new JTable(new MyTableModel());
                    //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                    JScrollPane jscrlp = new JScrollPane(jTabCars);
                    //Устаналиваем размеры прокручиваемой области
                    jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                    //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                    jfrm.getContentPane().add(jscrlp);
                    //Отображаем контейнер
                    jfrm.setVisible(true);
                }
             
                //Функция main, запускающаяся при старте приложения
                public static void main(String[] args) {
                    //Создаем фрейм в потоке обработки событий
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MainJtab1();
                        }
                    });
                }
            }



добавим метод getColumnName() в класс MyTableModel
--------------------------------------------------

            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import javax.swing.JFrame;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;
            import javax.swing.SwingUtilities;
            import javax.swing.table.AbstractTableModel;

            class MyTableModel extends AbstractTableModel{
                @Override
                public int getRowCount() {
                    return 12;
                }
                @Override
                public int getColumnCount() {
                    return 12;
                }
                @Override
                public Object getValueAt(int r, int c) {
                    return r * c;
                }
                
                @Override
                public String getColumnName(int c) {
                    switch (c) {
                        case 0:
                            return "id";
                        case 1:
                            return "model";
                        case 2:
                            return "brand";
                        case 3:
                            return "price";
                        case 4:
                            return "built_date";
                        case 5:
                            return "gear";
                        case 6:
                            return "seat";
                        case 7:
                            return "wheels";
                        case 8:
                            return "miles";
                        case 9:
                            return "capacity";
                        case 10:
                            return "sold";
                        case 11:
                            return "sold_on";
                        default:
                            return "Other Column";
                    }
                }
            }
            public class MainJtab2 {

                /**
                 * @param args
                 */
                //Массив содержащий заголоки таблицы
                Object[] headers = { "id","model","brand","price","built_date","gear","seat","wheels","miles","capacity","sold","sold_on" };
                 
                //Массив содержащий информацию для таблицы
                Object[][] data = {
                    { "1","Accord LX","Hyundai","25000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "3","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "4","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "5","Accord LX","Hyundai","12000.0","2007-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    { "6","Honda","Hyundai","10000.0","2010-01-01 10:00:00","1","4","4","12000","2","0","0000-00-00 00:00:00" },
                    { "7","Honda","Hyundai","11000.0","2012-01-01 10:00:00","1","4","4","1000","2","0","0000-00-00 00:00:00" },
                    { "8","Honda Sed","Hyundai","13000.0","2013-01-01 10:00:00","1","4","4","10000","2","0","0000-00-00 00:00:00" },
                    
                };
             
                //Объект таблицы
                JTable jTabCars;
             
                MainJtab2() {
                    //Создаем новый контейнер JFrame
                    JFrame jfrm = new JFrame("JTableCars");
                    //Устанавливаем диспетчер компоновки
                    jfrm.getContentPane().setLayout(new FlowLayout());
                    //Устанавливаем размер окна
                    jfrm.setSize(1000, 200);
                    //Устанавливаем завершение программы при закрытии окна
                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //Создаем новую таблицу на основе двумерного массива данных и заголовков
                    // jTabCars = new JTable(data, headers);
                    jTabCars = new JTable(new MyTableModel());
                    //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                    JScrollPane jscrlp = new JScrollPane(jTabCars);
                    //Устаналиваем размеры прокручиваемой области
                    jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                    //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                    jfrm.getContentPane().add(jscrlp);
                    //Отображаем контейнер
                    jfrm.setVisible(true);
                }
             
                //Функция main, запускающаяся при старте приложения
                public static void main(String[] args) {
                    //Создаем фрейм в потоке обработки событий
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MainJtab2();
                        }
                    });
                }
            }

На основе этих знаний попробуем создать таблицу, отображающую полезные данные. Например, у нас есть класс Car, у которого есть 12 полей.

            import java.util.Calendar;
            import java.util.Date;
            import java.util.GregorianCalendar;

            public class Car {

                private int id; // id
                private String model; // The model of the car as a string.
                private String brand; // The make of the car as a string
                private double price;
                private Date built_date; // The integral year the car was built.
                private int gear; // передача
                private int seat;
                private int wheels; // колесо - An integer representing the number of wheels the car has.
                private int miles; // The integral number of miles driven on the car.
                private int capacity;
                protected boolean sold = false;    
                protected Date sold_on; // Дата - транспортное средство было продано


                public Car(int id, String model, String brand, double price, DAte built_date, int gear,int seat, int wheels, int miles, int capacity, boolean sold, Date sold_on) { //конструктор) 
                    this.id = id;
                    this.model = model;
                    this.brand = brand;
                    this.price = price;
                    this.built_date = built_date;
                    this.gear = gear;
                    this.seat = seat;
                    this.wheels = wheels;
                    this.miles = miles;
                    this.capacity = ;
                    this.sold = sold;
                    this.sold_on = sold_on;
                }
                
             
                public int getCapacity(){
                    return capacity;
                    }

                public double getPrice(){
                    return price;
                    }
                
                public void setPrice(double price){
                    this.price = price;
                    }
                
                public double salePrice(){
                    // Return the sale price for this vehicle as a float amount.
                            
                    if (sold != false){
                        return 0.0; // Already sold
                    }
                    else {
                    return this.getWheel()*5000.0;
                    }
                  }
                public double salePrice(double amt){
                    // Return the sale price for this vehicle as a float amount.
                    
                    if (sold != false){
                        return 0.0; // Already sold
                    }
                    else {
                    return this.getWheel()*amt;
                    }
                  }
                public void setSold(Date d) {
                    this.sold_on = d;
                    this.sold = true;
                } 

                public int getId() {
                    return id;
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
                    return seat;
                }

                public void setNumberOfSeat(int numberOfSeat) {
                    this.seat = numberOfSeat;
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

            }

Будем хранить список сущностей класса Car, используя ArrayList. Наша таблица должна отображать информацию о каждой сущности.  Для начала изменим модель. Добавим поле cars в нашу модель, а так же изменим конструктор модели:


    ArrayList<Car> cars;
    MyTableModel(ArrayList<Car> cars) {
        super();
        this.cars = cars;
    }

Теперь нужно изменить количество столбцов и строк. Количеством строк будет количество наших сущностей. Количество столбцов — 12.
   
    @Override
    public int getRowCount() {
        // return 12;
        return cars.size();
    }
    @Override
    public int getColumnCount() {
        return 12;
    }

метод getValueAt()
------------------
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return cars.get(r).getId();
            case 1:
                return cars.get(r).getModel();
            case 2:
                return cars.get(r).getBrand();
            case 3:
                return cars.get(r).getPrice();
            case 4:
                return cars.get(r).getDateWasBuilt();
            case 5:
                return cars.get(r).getGear();
            case 6:
                return cars.get(r).getSeat();
            case 7:
                return cars.get(r).getWheels();
            case 8:
                return cars.get(r).getMiles();
            case 9:
                return cars.get(r).getCapacity();
            case 10:
                return cars.get(r).getSold();
            case 11:
                return cars.get(r).getSoldOn();

            default:
                return "";
        }
    }


                import java.awt.Dimension;
                import java.awt.FlowLayout;
                import javax.swing.JFrame;
                import javax.swing.JScrollPane;
                import javax.swing.JTable;
                import javax.swing.SwingUtilities;
                import javax.swing.table.AbstractTableModel;

                class MyTableModel extends AbstractTableModel{
                    
                    ArrayList<Car> cars;
                        MyTableModel(ArrayList<Car> cars) {
                            super();
                            this.cars = cars;
                        }
                    
                    @Override
                    public int getRowCount() {
                        // return 12;
                        return cars.size();
                    }
                    @Override
                    public int getColumnCount() {
                        return 12;
                    }
                    /*@Override
                    public Object getValueAt(int r, int c) {
                        return r * c;
                    }*/
                    @Override
                    public Object getValueAt(int r, int c) {
                        switch (c) {
                            case 0:
                                return cars.get(r).getId();
                            case 1:
                                return cars.get(r).getModel();
                            case 2:
                                return cars.get(r).getBrand();
                            case 3:
                                return cars.get(r).getPrice();
                            case 4:
                                return cars.get(r).getDateWasBuilt();
                            case 5:
                                return cars.get(r).getGear();
                            case 6:
                                return cars.get(r).getSeat();
                            case 7:
                                return cars.get(r).getWheels();
                            case 8:
                                return cars.get(r).getMiles();
                            case 9:
                                return cars.get(r).getCapacity();
                            case 10:
                                return cars.get(r).getSold();
                            case 11:
                                return cars.get(r).getSoldOn();

                            default:
                                return "";
                        }
                    }
                    
                    @Override
                    public String getColumnName(int c) {
                        switch (c) {
                            case 0:
                                return "id";
                            case 1:
                                return "model";
                            case 2:
                                return "brand";
                            case 3:
                                return "price";
                            case 4:
                                return "built_date";
                            case 5:
                                return "gear";
                            case 6:
                                return "seat";
                            case 7:
                                return "wheels";
                            case 8:
                                return "miles";
                            case 9:
                                return "capacity";
                            case 10:
                                return "sold";
                            case 11:
                                return "sold_on";
                            default:
                                return "Other Column";
                        }
                    }
                    
                  
                }
                public class MainJtab3 {

                    /**
                     * @param args
                     */

                 
                    //Объект таблицы
                    JTable jTabCars;
                 
                    MainJtab3() {
                       //Создаем новый контейнер JFrame
                        JFrame jfrm = new JFrame("JTableCars");
                        //Устанавливаем диспетчер компоновки
                        jfrm.getContentPane().setLayout(new FlowLayout());
                        //Устанавливаем размер окна
                        jfrm.setSize(1000, 200);
                        //Устанавливаем завершение программы при закрытии окна
                        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ArrayList<Car> cars = new ArrayList<>();
                    
                        cars.add(new Car(1,"Accord LX","Hyundai",25000,new Date(20080101),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(2,"Honda","Hyundai",25000,new Date(100801010),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(3,"Honda Sed","Hyundai",21000,new Date(2009010110),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(4,"Accord LX1","Hyundai",21100,new Date(2005010110),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(5,"Accord LX2","Hyundai",21500,new Date(2007010110),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(6,"Accord LX3","Hyundai",22200,new Date(2010010110),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(7,"Accord LX4","Hyundai",24000,new Date(2012010110),1,4,4,10000,2,false,new Date(0000)));
                        cars.add(new Car(8,"Accord LX5","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                        
                        //Создаем новую таблицу на основе двумерного массива данных и заголовков
                        // jTabCars = new JTable(data, headers);
                        jTabCars = new JTable(new MyTableModel(cars));
                        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                        JScrollPane jscrlp = new JScrollPane(jTabCars);
                        //Устаналиваем размеры прокручиваемой области
                        jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                        jfrm.getContentPane().add(jscrlp);
                        //Отображаем контейнер
                        jfrm.setVisible(true);
                    }
                 
                    //Функция main, запускающаяся при старте приложения
                    public static void main(String[] args) {
                        //Создаем фрейм в потоке обработки событий
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new MainJtab3();
                            }
                        });
                    }
                }


Создадим список из сущностей класса Car

        cars.add(new Car(1,"Accord LX","Hyundai",25000,new Date(20080101),1,4,4,10000,2,false,new Date(0000)));

        //Создадим модель таблицы
        tModel = new MyTableModel(cars);
        
        //На основе модели, создадим новую JTable
        jTabCars = new JTable(tModel);

            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import javax.swing.JFrame;
            import javax.swing.JButton;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;
            import javax.swing.SwingUtilities;
            import javax.swing.table.AbstractTableModel;
            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;

            import java.util.ArrayList;

            import java.util.Calendar;
            import java.util.Date;
            import java.util.GregorianCalendar;
            import java.text.SimpleDateFormat;

            class MyTableModel extends AbstractTableModel{
                
                ArrayList<Car> cars;
                    MyTableModel(ArrayList<Car> cars) {
                        super();
                        this.cars = cars;
                    }
                
                @Override
                public int getRowCount() {
                    // return 12;
                    return cars.size();
                }
                @Override
                public int getColumnCount() {
                    return 12;
                }
                /*@Override
                public Object getValueAt(int r, int c) {
                    return r * c;
                }*/
                @Override
                public Object getValueAt(int r, int c) {
                    switch (c) {
                        case 0:
                            return cars.get(r).getId();
                        case 1:
                            return cars.get(r).getModel();
                        case 2:
                            return cars.get(r).getBrand();
                        case 3:
                            return cars.get(r).getPrice();
                        case 4:
                            return cars.get(r).getDateWasBuilt();
                        case 5:
                            return cars.get(r).getGear();
                        case 6:
                            return cars.get(r).getSeat();
                        case 7:
                            return cars.get(r).getWheels();
                        case 8:
                            return cars.get(r).getMiles();
                        case 9:
                            return cars.get(r).getCapacity();
                        case 10:
                            return cars.get(r).getSold();
                        case 11:
                            return cars.get(r).getSoldOn();

                        default:
                            return "";
                    }
                }
                
                @Override
                public String getColumnName(int c) {
                    switch (c) {
                        case 0:
                            return "id";
                        case 1:
                            return "model";
                        case 2:
                            return "brand";
                        case 3:
                            return "price";
                        case 4:
                            return "built_date";
                        case 5:
                            return "gear";
                        case 6:
                            return "seat";
                        case 7:
                            return "wheels";
                        case 8:
                            return "miles";
                        case 9:
                            return "capacity";
                        case 10:
                            return "sold";
                        case 11:
                            return "sold_on";
                        default:
                            return "Other Column";
                    }
                }
                
              
            }
            public class MainJtab4 {

                //Объект таблицы
                JTable jTabCars;
                ArrayList<Car> cars = new ArrayList<>();
                MyTableModel tModel;
                MainJtab4() {
                    //Создаем новый контейнер JFrame
                    JFrame jfrm = new JFrame("JTableCars");
                    //Устанавливаем диспетчер компоновки
                    jfrm.getContentPane().setLayout(new FlowLayout());
                    //Устанавливаем размер окна
                    jfrm.setSize(1000, 200);
                    //Устанавливаем завершение программы при закрытии окна
                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
                    cars.add(new Car(1,"Accord LX","Hyundai",25000,new Date(20080101),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(2,"Honda","Hyundai",25000,new Date(100801010),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(3,"Honda Sed","Hyundai",21000,new Date(2009010110),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(4,"Accord LX1","Hyundai",21100,new Date(2005010110),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(5,"Accord LX2","Hyundai",21500,new Date(2007010110),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(6,"Accord LX3","Hyundai",22200,new Date(2010010110),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(7,"Accord LX4","Hyundai",24000,new Date(2012010110),1,4,4,10000,2,false,new Date(0000)));
                    cars.add(new Car(8,"Accord LX5","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                    
                    //Создаем новую таблицу на основе двумерного массива данных и заголовков
                    // jTabCars = new JTable(data, headers);
                    tModel = new MyTableModel(cars);
                    jTabCars = new JTable(tModel);
                    
                    //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                    JScrollPane jscrlp = new JScrollPane(jTabCars);
                    //Устаналиваем размеры прокручиваемой области
                    jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                    //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                    jfrm.getContentPane().add(jscrlp);
                    //Отображаем контейнер
                    jfrm.setVisible(true);
                    
                    JButton btnPress = new JButton("Click!");
                    btnPress.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            cars.add(new Car(9,"Accord LX6","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                            tModel.fireTableDataChanged();
                        }
                    });
                    jfrm.add(btnPress);
                    
                }
                
                //Функция main, запускающаяся при старте приложения
                public static void main(String[] args) {
                    //Создаем фрейм в потоке обработки событий
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MainJtab4();
                        }
                    });
                }
            }

ActionEvent событие
-------------------
Каждое приложение, которое имеет графический интерфейс пользователя не может обходиться без кнопок. В Java Swing кнопка представлена классом JButton. У кнопки имеются различные методы для ее конфигурирования — установка надписи на JButton, установка иконки, выравнивание текста, установка размеров и так далее. Кроме всего прочего разработчику необходимо навесить на JButton слушателя, который будет выполняться как только пользователь нажмет на кнопку.

Как только пользователь нажимает кнопку, создается ActionEvent событие, которое передается слушателям кнопки. Для того, чтобы организовать слушателя Swing предоставляет интерфейс ActionListener, который необходимо реализовать. 

Интерфейс ActionListener
------------------------
Интерфейс ActionListener требует только реализации одного метода — actionPerformed. 

public class TestActionListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
          //Код, который нужно выполнить при нажатии
     }
}

метод addActionListener
-----------------------
После того, как обработчик создан, его необходимо добавить к кнопке. Делается это при помощи метода addActionListener. В качестве параметра методу передается обработчик. Например, это можно сделать вот так:

JButton button = new JButton("Test button");
ActionListener actionListener = new TestActionListener();
button.addActionListener(actionListener);

метод fireTableDataChanged()
----------------------------
Таблица запрашивает у модели данные, только в случаи каких-либо событий. Если мы просто добавим в список новую сущность, ничего не произойдет. Для того, что бы таблица узнала об изменении данных, нужно вызвать у модели метод fireTableDataChanged(). Он «пошлет сигнал», что данные изменились и тогда таблице придется перерисовать записи. 


        import java.awt.Dimension;
        import java.awt.FlowLayout;
        import javax.swing.JFrame;
        import javax.swing.JButton;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;
        import javax.swing.SwingUtilities;
        import javax.swing.table.AbstractTableModel;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import java.util.ArrayList;
        import java.sql.*;

        import java.util.Date;

        class MyTableModel extends AbstractTableModel{
            
            ArrayList<Car> cars;
                MyTableModel(ArrayList<Car> cars) {
                    super();
                    this.cars = cars;
                }
            
            @Override
            public int getRowCount() {
                
                return cars.size();
            }
            @Override
            public int getColumnCount() {
                return 12;
            }
         
            @Override
            public Object getValueAt(int r, int c) {
                switch (c) {
                    case 0:
                        return cars.get(r).getId();
                    case 1:
                        return cars.get(r).getModel();
                    case 2:
                        return cars.get(r).getBrand();
                    case 3:
                        return cars.get(r).getPrice();
                    case 4:
                        return cars.get(r).getDateWasBuilt();
                    case 5:
                        return cars.get(r).getGear();
                    case 6:
                        return cars.get(r).getSeat();
                    case 7:
                        return cars.get(r).getWheels();
                    case 8:
                        return cars.get(r).getMiles();
                    case 9:
                        return cars.get(r).getCapacity();
                    case 10:
                        return cars.get(r).getSold();
                    case 11:
                        return cars.get(r).getSoldOn();

                    default:
                        return "";
                }
            }
            
            @Override
            public String getColumnName(int c) {
                switch (c) {
                    case 0:
                        return "id";
                    case 1:
                        return "model";
                    case 2:
                        return "brand";
                    case 3:
                        return "price";
                    case 4:
                        return "built_date";
                    case 5:
                        return "gear";
                    case 6:
                        return "seat";
                    case 7:
                        return "wheels";
                    case 8:
                        return "miles";
                    case 9:
                        return "capacity";
                    case 10:
                        return "sold";
                    case 11:
                        return "sold_on";
                    default:
                        return "Other Column";
                }
            }
        }

        public class MainJtab5 {

            /**
             * @param args
             */
            //Объект таблицы
            JTable jTabCars;
            ArrayList<Car> cars = new ArrayList<>();
            //ArrayList<Car> results = new ArrayList<Car>();
            MyTableModel tModel;
            
            MainJtab5() {
                //Создаем новый контейнер JFrame
                JFrame jfrm = new JFrame("JTableCars");
                //Устанавливаем диспетчер компоновки
                jfrm.getContentPane().setLayout(new FlowLayout());
                //Устанавливаем размер окна
                jfrm.setSize(1000, 200);
                //Устанавливаем завершение программы при закрытии окна
                jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Connection c = null;
                Statement stmt = null;

                try {
                  Class.forName("org.sqlite.JDBC");
                  c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
                  c.setAutoCommit(false);
                  System.out.println("Opened database successfully");

                  stmt = c.createStatement();
                  String sql = "UPDATE cars set price = 25000.00 where id=1;";
                  stmt.executeUpdate(sql);
                  c.commit();

                  ResultSet resultset = stmt.executeQuery( "SELECT * FROM cars;" );
             
                  while (resultset.next()) {
          
                       cars.add(new Car(resultset.getInt("id"),
                                   resultset.getString("model"),
                                   resultset.getString("brand"),
                                   resultset.getFloat("price"),
                                   new Date(2005010110),
                                   resultset.getInt("gear"),
                                   resultset.getInt("seat"),
                                   resultset.getInt("wheels"),
                                   resultset.getInt("miles"),
                                   resultset.getInt("capacity"),
                                   resultset.getBoolean("sold"),
                                   new Date(0)
                           ));
                   }
                  resultset.close();
                  stmt.close();
                  c.close();
                } catch ( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  System.exit(0);
                }
                //Создаем новую таблицу на основе двумерного массива данных и заголовков
                tModel = new MyTableModel(cars);
                jTabCars = new JTable(tModel);
                
                //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                JScrollPane jscrlp = new JScrollPane(jTabCars);
                //Устаналиваем размеры прокручиваемой области
                jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                jfrm.getContentPane().add(jscrlp);
                //Отображаем контейнер
                jfrm.setVisible(true);
                
                JButton btnPress = new JButton("Click!");
                btnPress.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        cars.add(new Car(9,"Accord LX6","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                        tModel.fireTableDataChanged();
                    }
                });
                jfrm.add(btnPress);
                
            }
            
            //Функция main, запускающаяся при старте приложения
            public static void main(String[] args) {
                //Создаем фрейм в потоке обработки событий
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new MainJtab5();
                    }
                });
            }
        }



            import java.awt.Dimension;
            import java.awt.FlowLayout;
            import javax.swing.JFrame;
            import javax.swing.JButton;
            import javax.swing.JScrollPane;
            import javax.swing.JTable;
            import javax.swing.SwingUtilities;
            import javax.swing.table.AbstractTableModel;
            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;

            import java.util.ArrayList;
            import java.sql.*;

            import java.util.Date;

            class MyTableModel extends AbstractTableModel{
                
                ArrayList<Car> cars;
                    MyTableModel(ArrayList<Car> cars) {
                        super();
                        this.cars = cars;
                    }
                
                @Override
                public int getRowCount() {
                    
                    return cars.size();
                }
                @Override
                public int getColumnCount() {
                    return 12;
                }
             
                @Override
                public Object getValueAt(int r, int c) {
                    switch (c) {
                        case 0:
                            return cars.get(r).getId();
                        case 1:
                            return cars.get(r).getModel();
                        case 2:
                            return cars.get(r).getBrand();
                        case 3:
                            return cars.get(r).getPrice();
                        case 4:
                            return cars.get(r).getDateWasBuilt();
                        case 5:
                            return cars.get(r).getGear();
                        case 6:
                            return cars.get(r).getSeat();
                        case 7:
                            return cars.get(r).getWheels();
                        case 8:
                            return cars.get(r).getMiles();
                        case 9:
                            return cars.get(r).getCapacity();
                        case 10:
                            return cars.get(r).getSold();
                        case 11:
                            return cars.get(r).getSoldOn();

                        default:
                            return "";
                    }
                }
                
                @Override
                public String getColumnName(int c) {
                    switch (c) {
                        case 0:
                            return "id";
                        case 1:
                            return "model";
                        case 2:
                            return "brand";
                        case 3:
                            return "price";
                        case 4:
                            return "built_date";
                        case 5:
                            return "gear";
                        case 6:
                            return "seat";
                        case 7:
                            return "wheels";
                        case 8:
                            return "miles";
                        case 9:
                            return "capacity";
                        case 10:
                            return "sold";
                        case 11:
                            return "sold_on";
                        default:
                            return "Other Column";
                    }
                }
            }

            public class MainJtab5 {

                //Объект таблицы
                JTable jTabCars;
                ArrayList<Car> cars = new ArrayList<>();

                MyTableModel tModel;
                
                MainJtab5() {
                    //Создаем новый контейнер JFrame
                    JFrame jfrm = new JFrame("JTableCars");
                    //Устанавливаем диспетчер компоновки
                    jfrm.getContentPane().setLayout(new FlowLayout());
                    //Устанавливаем размер окна
                    jfrm.setSize(1000, 200);
                    //Устанавливаем завершение программы при закрытии окна
                    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                    Connection c = null;
                    Statement stmt = null;

                    try {
                      Class.forName("org.sqlite.JDBC");
                      c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");

                      System.out.println("Opened database successfully");

                      stmt = c.createStatement();

                      ResultSet resultset = stmt.executeQuery( "SELECT * FROM cars;" );
                 
                      while (resultset.next()) {
              
                           cars.add(new Car(resultset.getInt("id"),
                                       resultset.getString("model"),
                                       resultset.getString("brand"),
                                       resultset.getFloat("price"),
                                       new Date(2005010110),
                                       resultset.getInt("gear"),
                                       resultset.getInt("seat"),
                                       resultset.getInt("wheels"),
                                       resultset.getInt("miles"),
                                       resultset.getInt("capacity"),
                                       resultset.getBoolean("sold"),
                                       new Date(0)
                               ));
                       }
                      resultset.close();
                      stmt.close();
                      c.close();
                    } catch ( Exception e ) {
                      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                      System.exit(0);
                    }
                    //Создаем новую таблицу на основе двумерного массива данных и заголовков
                    tModel = new MyTableModel(cars);
                    jTabCars = new JTable(tModel);
                    
                    //Создаем панель прокрутки и включаем в ее состав нашу таблицу
                    JScrollPane jscrlp = new JScrollPane(jTabCars);
                    //Устаналиваем размеры прокручиваемой области
                    jTabCars.setPreferredScrollableViewportSize(new Dimension(1000, 200));
                    //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
                    jfrm.getContentPane().add(jscrlp);
                    //Отображаем контейнер
                    jfrm.setVisible(true);
                    
                    JButton btnPress = new JButton("Click!");
                    btnPress.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            cars.add(new Car(9,"Accord LX6","Hyundai",25500,new Date(2011010110),1,4,4,10000,2,false,new Date(0000)));
                            tModel.fireTableDataChanged();
                        }
                    });
                    jfrm.add(btnPress);
                    
                }
                
                //Функция main, запускающаяся при старте приложения
                public static void main(String[] args) {
                    //Создаем фрейм в потоке обработки событий
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new MainJtab5();
                        }
                    });
                }
            }

