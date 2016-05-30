# java Unit 13

Наша база vehicle.db работает под управлением sqlite, JDBC драйвер для этой СУБД называется sqlite.JDBC. 

Создадим класс, который соединит нас с базой данных. 

код класса Db:
--------------
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

        public class Db {
            
                private Connection con = null;

                public Db(String driver, String url)
                {
                    try
                    {
                        Class.forName(driver);
                        con = DriverManager.getConnection(url);

                    } catch (ClassNotFoundException ex)
                    {
                        System.err.println("Db.Cannot find this db driver classes.");
                            ex.printStackTrace();
                    } catch (SQLException e)
                    {
                        System.err.println("Db.Cannot connect to this db.");
                            e.printStackTrace();        
                      }         
                }
        }

Мы объявили в конструкторе класса параметры:

driver – это имя нашего драйвера sqlite;
url – адрес базы данных, у нас это адрес базы "jdbc:sqlite:vehicle.db"; 

В блоке try/catch загружаем статическим методом forName дравер для базы данных. Статическим методом getConnection устанавливаем соединение с нашей базой данных. Класс DriverManager управляет работой драйверов. Если драйвер не обнаружится, то будет выброшено исключение ClassNotFoundException, а если DriverManager не сможет соединиться с нашей базой, то будет выброшено исключение SQLException.

Поле con, имеющее тип Connection, можно представить как телефонный провод, по которому информация будет перетекать из нашего приложения в базу данных и обратно.

Vector
======

Часто перед программистом встает задача организации массива с неизвестным заранее размером. Стандартные массивы в Java не подходят для решения этой задачи, так как их размер должен быть указан в исходном тексте программы явным образом при выделении памяти.
Для организации массива с динамически изменяющимся размером вы можете воспользоваться классом java.util.Vector. 

    import java.util.Vector;

Такой массив может хранить объекты любого класса.
При создании объекта класса Vector размер соответствующего массива можно не указывать. В этом случае будет создан массив с размером, принятом по умолчанию. Когда вы будете добавлять в него новые элементы, размер массива при необходимости будет автоматически увеличиваться.

Выбрав соответствующий конструктор, программист может задать как начальный размер массива, так и величину, на которую происходит увеличение этого размера.

С помощью методов, определенных в классе Vector, вы можете добавлять в массив новые элементы (как в конец массива, так и в произвольную ячейку со сдвигом остальных ячеек), извлекать элемент по номеру ячейки, выполнять поиск, удаление элементов и так далее.

Конструкторы
------------

Vector()
           Создаёт пустой вектор размером 10 и с capacityIncrement = 0.

Vector(Collection c)
           Создаёт вектор содержащий элементы определённой коллекции.

Vector(int initialCapacity)
           Создает пустой вектор с заданным объемом памяти.

Vector(int initialCapacity, int capacityIncrement)
           Создает пустой вектор с заданным объемом памяти (initialCapacity) и увеличением объема (capacityIncrement).


Как добавить элемент в вектор
-----------------------------

Методы
------

void add(int index, Object element) 
           Добавляет в определённую позицию вектора определённый элемент.

boolean add(Object o) 
           Добавляет в конец вектора определённый элемент.


boolean addAll(Collection c) 
           Добавляет в вектор все элементы определённой коллекции.

boolean addAll(int index, Collection c) 
           Добавляет в вектор в определённую позицию все элементы коллекции.

void addElement(Object obj) 
           Добавляет определённый компонент в конец вектора, увеличивая размер вектора.


        import java.util.Vector;

        public class VectorExample {
            
            //Имена колонок;
            protected Vector<String> columnNames;

            public void addCharacterandPrint(){
                 columnNames = new Vector<String>();
                 columnNames.add("#");
                 columnNames.add("Model");
                 columnNames.add("Brand");
                 columnNames.add("Price");
                 columnNames.add("Built Date");
                 columnNames.add("Geer");
                 columnNames.add("Seats");
                 columnNames.add("Wheels");
                 columnNames.add("Miles");
                 columnNames.add("Capacity");
                 columnNames.add("Sold");
                 columnNames.add("Sold On");



                for(int i=0;i<columnNames.size();i++){
                System.out.println("The characters are\t"+columnNames.get(i));
                }
            }
            public static void main(String args[]){
                VectorExample example=new VectorExample();
                example.addCharacterandPrint();
                }
        }


Vector Column Count
-------------------

        import java.util.Vector;

        public class VectorExampleColumnCount {

            //Имена колонок;
            protected Vector<String> columnNames;

            public void addCharacterandPrint(){
                 columnNames = new Vector<String>();
                 columnNames.add("#");
                 columnNames.add("Model");
                 columnNames.add("Brand");
                 columnNames.add("Price");
                 columnNames.add("Built Date");
                 columnNames.add("Geer");
                 columnNames.add("Seats");
                 columnNames.add("Wheels");
                 columnNames.add("Miles");
                 columnNames.add("Capacity");
                 columnNames.add("Sold");
                 columnNames.add("Sold On");

                for(int i=0;i<columnNames.size();i++){
                System.out.println("The characters are\t"+columnNames.get(i));
                }
            }

            public int getColumnCount() 
            {
                    return columnNames.size();
            }
            
            public static void main(String args[]){
                VectorExampleColumnCount example=new VectorExampleColumnCount();
                
                example.addCharacterandPrint();
                System.out.println("The Column Count are\t"+example.getColumnCount());
                }

        }


Показывать заголовки колонок
----------------------------
        import java.util.Vector;

        public class VectorExampleColumnName {

            //Имена колонок;
            protected Vector<String> columnNames;

            public void addCharacterandPrint(){
                 columnNames = new Vector<String>();
                 columnNames.add("#");
                 columnNames.add("Model");
                 columnNames.add("Brand");
                 columnNames.add("Price");
                 columnNames.add("Built Date");
                 columnNames.add("Geer");
                 columnNames.add("Seats");
                 columnNames.add("Wheels");
                 columnNames.add("Miles");
                 columnNames.add("Capacity");
                 columnNames.add("Sold");
                 columnNames.add("Sold On");

                for(int i=0;i<columnNames.size();i++){
                System.out.println("The characters are\t"+columnNames.get(i));
                }
            }

            public int getColumnCount() 
            {
                    return columnNames.size();
            }
            //Показывать заголовки колонок;
            public String getColumnName(int column)
            {
                    return columnNames.get(column);
            }

            
            public static void main(String args[]){
                VectorExampleColumnName example=new VectorExampleColumnName();
                
                example.addCharacterandPrint();
                System.out.println("The Column Count is\t"+example.getColumnCount());
                
                for (int i=0;i<example.getColumnCount();i++){
                System.out.println("The Column Name is\t"+example.getColumnName(i));
                }
            }

        }


Классы колонок
--------------


        import java.util.Vector;

        public class VectorExampleColumnClass {

            //Имена колонок;
            protected Vector<String> columnNames;
            
            //Классы колонок;
            protected Vector<Object> vColClass;

            public void addCharacterandPrint(){
                 columnNames = new Vector<String>();
                 columnNames.add("#");
                 columnNames.add("Model");
                 columnNames.add("Brand");
                 columnNames.add("Price");
                 columnNames.add("Built Date");
                 columnNames.add("Geer");
                 columnNames.add("Seats");
                 columnNames.add("Wheels");
                 columnNames.add("Miles");
                 columnNames.add("Capacity");
                 columnNames.add("Sold");
                 columnNames.add("Sold On");
                 vColClass = new Vector<Object>(); 
                 vColClass.add(0, Integer.class);
                 vColClass.add(1, String.class);
                 vColClass.add(2, String.class);
                 vColClass.add(3, Double.class);
                 vColClass.add(4, String.class);
                 vColClass.add(5, Integer.class);
                 vColClass.add(6, Integer.class);
                 vColClass.add(7, Integer.class);
                 vColClass.add(8, Integer.class);
                 vColClass.add(9, Integer.class);
                 vColClass.add(10, Integer.class);
                 vColClass.add(11, String.class);

                /*for(int i=0;i<columnNames.size();i++){
                System.out.println("The characters are\t"+columnNames.get(i));
                }*/
            }

            public int getColumnCount() 
            {
                    return columnNames.size();
            }
            //Показывать заголовки колонок;
            public String getColumnName(int column)
            {
                    return columnNames.get(column);
            }
            
            public Class<?> getColumnClass(int col)
            {
                    Class<?> c = Object.class;
                    try 
                    {
                            c = (Class<?>)vColClass.get(col);
                    } catch (RuntimeException e)
                    {
                            System.out.println(e);
                    }
                    return c;
            }

            
            public static void main(String args[]){
                VectorExampleColumnClass example=new VectorExampleColumnClass();
                
                example.addCharacterandPrint();
                System.out.println("The Column Count is\t"+example.getColumnCount());
                
                for (int i=0;i<example.getColumnCount();i++){
                System.out.println("The Column Name is\t"+example.getColumnName(i));
                
                }
                for (int i=0;i<example.getColumnCount();i++){
                    System.out.println("The Column Class is\t"+example.getColumnClass(i));
               }
                
            }

        }


Данные
-------

   //Данные;
    private Vector<Vector<Object>> tableData;

    public Vector<Vector<Object>> getTableData()
    {
            return tableData;
    }

    public void setTableData(Vector<Vector<Object>> tableData)
    {
            this.tableData = tableData;
    }


количество компонентов в векторе

int size() 
           Возвращает количество компонентов в векторе.


    public int getRowCount()
    {
            return getTableData().size();
    }


элемент вектора из определённой позиции

Object get(int index) 
           Возвращает элемент вектора из определённой позиции.

    public Object getValueAt(int row, int column) 
    {
            return getTableData().get(row).get(column);
    }


Заменяет объект

Object set(int index, Object element) 
           Заменяет объект в определянной позиции на другой.


Получим по Connection информацию из базы данных. 
------------------------------------------------
Введём поле st типа Statement. Это поле даст нам возможность обратиться к базе данных с каким-то конкретным запросом. Определим метод getContent, который вернёт информацию из базы данных:

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.sql.ResultSet;
        import java.util.Vector;

        public class Db {
            
                private Connection con = null;

                public Db(String driver, String url)
                {
                    try
                    {
                        Class.forName(driver);
                        con = DriverManager.getConnection(url);

                    } catch (ClassNotFoundException ex)
                    {
                        System.err.println("Db.Cannot find this db driver classes.");
                            ex.printStackTrace();
                    } catch (SQLException e)
                    {
                        System.err.println("Db.Cannot connect to this db.");
                            e.printStackTrace();        
                      }         
                }
                
                public Vector<Vector<Object>> getContent(String query)
                {
                    Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
                    try
                    {
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int cols = rsmd.getColumnCount();
                                    while (rs.next())
                                    {
                                            Vector<Object> newRow = new Vector<Object>();
                                            for (int i = 1; i <= cols; i++)
                                            {
                                                    newRow.add(rs.getObject(i));
                                            }
                                            retVector.add(newRow);
                                    }
                                    rs.close();
                        st.close();
                     } catch (SQLException e)
                     {
                        System.err.println("Db.There are problems with the query " + query);
                        e.printStackTrace();
                     }      
                    return retVector;
                }
        }


В методе getContent создаём объект Statement st, он позволит нам сделать запрос к базе, сам запрос выполняется методом executeQuery, который возвращает набор данных в виде объекта ResultSet rs. Этот набор данных можно представить как таблицу из базы данных, содержащую сами данные и информацию об этой таблице, например, сколько столбцов имеется в таблице. Информацию об устойстве полученного набора данных извлечём из объекта ResultSetMetaData rsmd. 

Нас интересует количество колонок в наборе данных. Для этого пишем строку: 

    int cols = rsmd.getColumnCount(). 

Метод rs.next() перемещает нас по строкам набора данных. Получив первую строку, в цикле for извлекаем значение из каждой ячейки строки методом rs.getObject(i) и добавляем его в вектор newVector. Сформированный таким образом newVector добавляем в результирующий вектор retVector. Вектор retVector становится изображением набора данных rs.


Перейдём к модели, которая будет хранить информацию из базы данных.

AbstractTableModel
------------------

        import java.util.Vector;
        import javax.swing.table.AbstractTableModel;
        import java.util.Date;

        public class Model extends AbstractTableModel {
            
            //Имена колонок;
            protected Vector<String> columnNames;
            
            //Данные;
            private Vector<Vector<Object>> tableData;
            
            //Классы колонок;
            protected Vector<Object> vColClass;
                
            public Model() {
                 super();
                 vColClass = new Vector<Object>(); 
                 vColClass.add(0, Integer.class);
                 vColClass.add(1, String.class);
                 vColClass.add(2, String.class);
                 vColClass.add(3, Double.class);
                 vColClass.add(4, String.class);
                 vColClass.add(5, Integer.class);
                 vColClass.add(6, Integer.class);
                 vColClass.add(7, Integer.class);
                 vColClass.add(8, Integer.class);
                 vColClass.add(9, Integer.class);
                 vColClass.add(10, Integer.class);
                 vColClass.add(11, String.class);

                 columnNames = new Vector<String>();
                 columnNames.add("#");
                 columnNames.add("Model");
                 columnNames.add("Brand");
                 columnNames.add("Price");
                 columnNames.add("Built Date");
                 columnNames.add("Geer");
                 columnNames.add("Seats");
                 columnNames.add("Wheels");
                 columnNames.add("Miles");
                 columnNames.add("Capacity");
                 columnNames.add("Sold");
                 columnNames.add("Sold On");
                        
            }
            
            @Override
            public int getColumnCount() 
            {
                    return columnNames.size();
            }

            @Override
            public int getRowCount()
            {
                    return getTableData().size();
            }

            @Override
            public Object getValueAt(int row, int column) 
            {
                    return getTableData().get(row).get(column);
            }

            //Показывать заголовки колонок;
            public String getColumnName(int column)
            {
                    return columnNames.get(column);
            }

            //Запрещаем редактировать первую колонку (счёт колонок идёт с нуля);
            public boolean isCellEditable(int row, int column)
            {
                    if(column == 0)
                    {
                            return false;
                    }
                    return true;

            }

            public void setValueAt(Object obj, int row, int column)
            {
             
              switch (column) {
            
                    case 0:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        (getTableData().get(row)).set(column, (Integer)obj);
                        break;

                    case 1:
                    case 2:
                        (getTableData().get(row)).set(column, (String)obj);
                        break;

                    case 3:
                    case 4:
                    case 11:
                        (getTableData().get(row)).set(column, (Double)obj);
                        break;
                    }
                    fireTableCellUpdated(row, column);
            }

            public Class<?> getColumnClass(int col)
            {
                    Class<?> c = Object.class;
                    try 
                    {
                            c = (Class<?>)vColClass.get(col);
                    } catch (RuntimeException e)
                    {
                            System.out.println(e);
                    }
                    return c;
            }

            public void setTableData(Vector<Vector<Object>> tableData)
            {
                    this.tableData = tableData;
            }

            public Vector<Vector<Object>> getTableData()
            {
                    return tableData;
            }
        }


Вернёмся к главному окну приложения

        import java.awt.Dimension;

        import javax.swing.BorderFactory;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JSplitPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.border.TitledBorder;
        import java.util.Vector;

        public class View {
            
            private Db dbase;
            private JTable table;
            private Model model;

            public View() {
                
                //Не забудьте указать свой путь к базе, вместо моего: jdbc:sqlite:vehicle.db;
                              
                dbase = new Db("org.sqlite.JDBC", "jdbc:sqlite:vehicle.db");
                
                // Create views swing UI components 
                JTextField searchTermTextField = new JTextField(26);
                JButton filterButton = new JButton("Filter");
                
                // Create table model
                model = new Model();
                model.setTableData(dbase.getNomen("SELECT * FROM cars"));
                
                table = new JTable();
                table.setModel(model);
                
                table.getColumnModel().getColumn(0).setMaxWidth(50);

                // Create controller
                Controller controller = new Controller(searchTermTextField, model);
                filterButton.addActionListener(controller);

                // Set the view layout
                JPanel ctrlPane = new JPanel();
                ctrlPane.add(searchTermTextField);
                ctrlPane.add(filterButton);

                JScrollPane tableScrollPane = new JScrollPane(table);
                tableScrollPane.setPreferredSize(new Dimension(1000, 400));
                tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Market Janus",
                        TitledBorder.CENTER, TitledBorder.TOP));

                JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
                splitPane.setDividerLocation(35);
                splitPane.setEnabled(false);

                // Display it all in a scrolling window and make the window appear
                JFrame frame = new JFrame("Swing MVC Market");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(splitPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        }


Методы
=======

int capacity() 
           Возвращает объем вектора.

void  clear() 
           Удаляет все элементы вектора.

Object clone() 
           Возвращает аналог вектора.

boolean contains(Object elem) 
           Возвращает значение true если компонент содержится в векторе.

boolean containsAll(Collection c) 
           Возвращает значение true если вектор содержит все элементы коллекции.

void    copyInto(Object[] anArray) 
           Копирует элементы вектора в заданный массив.

Object  elementAt(int index) 
           Возвращает компонент по индексу.

Enumeration elements() 
           Возвращает перечисление компонентов вектора.

void    ensureCapacity(int minCapacity) 
           Увеличивает минимальный объём вектора.

boolean equals(Object o) 
           Сравнивает определённый объект с вектором.

Object  firstElement() 
           Возвращает первый элемент вектора (индекс 0).


int hashCode() 
           Возвращает хэш-код вектора.

int indexOf(Object elem) 
           Ищет аналог объекта и возвращает индекс первого найденого.

int indexOf(Object elem, int index) 
           Ищет аналог объекта, начиная с определённой позиции, и возвращает индекс первого найденого.

void    insertElementAt(Object obj, int index) 
           Вставляет объект в определённую позицию в векторе.

boolean isEmpty() 
           Возвращает true если вектор пуст.

Object  lastElement() 
           Возвращает поседний элемент вектора.

int lastIndexOf(Object elem) 
           Возвращает индекс последнего найденного аналога объекта.

int lastIndexOf(Object elem, int index) 
           Возвращает индекс последнего найденного аналога объекта, начиная поиск с определённой позиции.

Object  remove(int index) 
           Удаляет объект из определённой позиции в векторе.

boolean remove(Object o) 
           Удаляет первый найденный аналогичный объект.

boolean removeAll(Collection c) 
           Удаляет все объекты коллекции в векторе.

void    removeAllElements() 
           Удаляет все объекты и устанавливает размер равным нулю.

boolean removeElement(Object obj) 
           Удаляет первый найденный аналогичный компонент.

void    removeElementAt(int index) 
           Удаляет компонент из определённой позиции в векторе.

protected void  removeRange(int fromIndex, int toIndex) 
           Удаляет все объекты, начиная с объекта с индексом fromIndex и заканчивая объектом с индексом toIndex.

boolean retainAll(Collection c) 
           Удаляет все объекты кроме объектов содержащихся в коллекции.

void    setElementAt(Object obj, int index) 
           Заменяет компонент в определянной позиции на другой.

void    setSize(int newSize) 
           Устанавливает размер вектора.

List    subList(int fromIndex, int toIndex) 
           Возвращает часть вектора начиная с объекта с индексом fromIndex и заканчивая объектом с индексом toIndex.

Object[]    toArray() 
           Возвращает массив содержащий все элементы вектора.

Object[]    toArray(Object[] a) 
           Возвращает массив содержащий все элементы вектора.

String  toString() 
           Возвращает представление вектора в виде строки.

void    trimToSize() 
           Приводит объем вектора к заданному размеру.

