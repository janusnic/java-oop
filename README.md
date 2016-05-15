# java Unit 09

Работа с SQLite
===============

SQLite – это реляционная база данных, запросы к которой можно осуществлять при помощи языка запросов SQL. База данных не поддерживает все особенности SQL и уступает в функциональности другим развитым СУБД, но вполне подходит для хранения и извлечения информации.
Все базы данных хранятся в файлах, по одному файлу на базу. Количество баз данных, а так же таблиц в них, ограниченно только свободным местом, имеющимся на сайте. А максимально возможный объём одной базы данных составляет 2 Тб.

Так как все данные хранятся в файлах, проблем с переносом базы данных с одного хостинга на другой не существует – достаточно лишь скопировать соответствующие файлы.

Работа с sqlite3
----------------

Для установки sqlite3 на Linux выполняем команду:

sudo apt-get install sqlite3 libsqlite3-dev

В результате на вашей машине будет установлен sqlite3. Для установки данного инструмента на других ОС следуйте инструкциям http://www.sqlite.org/download.html. Для запуска sqlite выполняем команду sqlite3 в консоли.

Мета Команды
------------
Мета Команды - предназначены для формирования таблиц и других административных операций. Все они оканчиваются точкой.

.show   Показывает текущие настройки заданных параметров
.databases  Показывает название баз данных и файлов
.quit   Выход из sqlite3
.tables Показывает текущие таблицы
.schema Отражает структуру таблицы
.header Отобразить или скрыть шапку таблицы
.mode   Выбор режима отображения данных таблицы
.dump   Сделать копию базы данных в текстовом формате

Стандартные команды
-------------------
предназначены для взаимодействия с базой данных. Стандартные команды могут быть классифицированы по трём группам:

Язык описания данных DDL
------------------------
Язык описания данных DDL: команды для создания таблицы, изменения и удаления баз данных, таблиц и прочего.

        CREATE
        ALTER
        DROP
Язык управления данными DML
----------------------------
Язык управления данными DML: позволяют пользователю манипулировать данными (добавлять/изменять/удалять).
        INSERT
        UPDATE
        DELETE
Язык запросов DQL
-----------------
Язык запросов DQL: позволяет осуществлять выборку данных.
        SELECT

SQLite так же поддерживает и множество других команд, список которых можно найти тут - https://www.sqlite.org/lang.html.

Файлы баз данных SQLite являются кроссплатформенными. Они могут располагаться на различного рода устройствах.

Connect to SQLite via JDBC
==========================
https://bitbucket.org/xerial/sqlite-jdbc/downloads

sqlite-jdbc-3.8.11.2.jar

JDBC (Java DataBase Connectivity) – стандарт для связи Java-программы с базой данных, подключение происходит с помощью так называемых драйверов. 

нужно подключить драйвер sqlite к проекту: 

создаем в корне проекта папку lib и копируем туда файл драйвера sqlite, в Eclipse в панели Package Explorer кликаем правой кнопкой мыши и выбираем Refresh.

После этого в Package Explorer появится папка lib и драйвер sqlite-jdbc-3.8.11.2.jar

Теперь драйвер нужно подключить к проекту, кликаем правой кнопкой мыши на название проекта в Package Explorer и выбираем Properties.

Выбираем пункт Java Build Path -> вкладка Libraries -> кнопка Add JARs…

Выделяем файл драйвера и кликаем на кнопке OK.


Connecting To Database
======================

создадим базу данных sqlite3:

    sqlite3 vehicle.db

В результате, в текущем каталоге у нас появится файл vehicle.db.
если не указать название файла, sqlite3 создаст временную базу данных.

class SQLiteJDBC1
-----------------
        import java.sql.*;

        public class SQLiteJDBC1
        {
          public static void main( String args[] )
          {
            Connection c = null;
            try {
              Class.forName("org.sqlite.JDBC");
              c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
            } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
            }
            System.out.println("Opened database successfully");
          }
        }


Создание таблицы
----------------
определимся с типами данных для каждой из колонок

        CREATE TABLE cars (
            id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
            model CHAR(50) NOT NULL,
            brand CHAR(50) NOT NULL,
            price REAL NOT NULL,
            built_date DATEYIME NOT NULL
            gear INTEGER NOT NULL,
            seat INTEGER NOT NULL,
            wheels INTEGER NOT NULL,
            miles INTEGER NOT NULL,
            capacity INTEGER NOT NULL, 
            sold BOOLEAN,
            sold_on DATETIME
             );


NOT NULL обеспечит уверенность, что ячейка не будет содержать пустое значение. PRIMARY KEY и AUTOINCREMENT расширяют возможности поля id.

Чтобы убедиться в том, что таблица была создана, выполняем мета команду .tables. В результате видим нашу таблицу cars.

Для получения структуры таблицы наберите .schema cars

class SQLiteJDBC2
-----------------
        import java.sql.*;

        public class SQLiteJDBC2
        {
          public static void main( String args[] )
              {
                Connection c = null;
                Statement stmt = null;
                try {
                  Class.forName("org.sqlite.JDBC");
                  c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
                  System.out.println("Opened database successfully");

                  stmt = c.createStatement();
                  String sql = "CREATE TABLE cars " +
                               "(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
                               " model          CHAR(50)    NOT NULL, " + 
                               " brand          CHAR(50)    NOT NULL, " +
                               " price          REAL        NOT NULL, " + 
                               " built_date     DATEYIME    NOT NULL, " + 
                               " gear           INTEGER     NOT NULL, " + 
                               " seat           INTEGER     NOT NULL, " + 
                               " wheels         INTEGER     NOT NULL, " + 
                               " miles          INTEGER     NOT NULL, " + 
                               " capacity       INTEGER     NOT NULL, " + 
                               " sold           BOOLEAN             , " + 
                               " sold_on        DATEYIME    NOT NULL)"; 
                  stmt.executeUpdate(sql);
                  stmt.close();
                  c.close();
                } catch ( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  System.exit(0);
                }
                System.out.println("Table created successfully");
              }
        }

ВСТАВКА СТРОК
=============
Для вставки воспользуемся командой INSERT.

        BEGIN TRANSACTION;
        CREATE TABLE cars (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL, model          CHAR(50)    NOT NULL,  brand          CHAR(50)    NOT NULL,  price          REAL        NOT NULL,  built_date     DATEYIME    NOT NULL,  gear           INTEGER     NOT NULL,  seat           INTEGER     NOT NULL,  wheels         INTEGER     NOT NULL,  miles          INTEGER     NOT NULL,  capacity       INTEGER     NOT NULL,  sold           BOOLEAN             ,  sold_on        DATEYIME    NOT NULL);
        INSERT INTO cars VALUES(1,'Accord LX','Hyundai',12000.0,'2007-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(2,'Honda','Hyundai',10000.0,'2010-01-01 10:00:00',1,4,4,12000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(3,'Honda','Hyundai',11000.0,'2012-01-01 10:00:00',1,4,4,1000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(4,'Honda Sed','Hyundai',13000.0,'2013-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(5,'Accord LX','Hyundai',12000.0,'2007-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(6,'Honda','Hyundai',10000.0,'2010-01-01 10:00:00',1,4,4,12000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(7,'Honda','Hyundai',11000.0,'2012-01-01 10:00:00',1,4,4,1000,2,0,'0000-00-00 00:00:00');
        INSERT INTO cars VALUES(8,'Honda Sed','Hyundai',13000.0,'2013-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00');
        CREATE TABLE sqlite_sequence(name,seq);
        INSERT INTO sqlite_sequence VALUES('cars',8);
        COMMIT;

Указывать значение для id не нужно т.к. оно сформируется автоматически благодаря настройке AUTOINCREMENT.

Теперь можем внести данные в таблицу.
------------------------------------

INSERT Operation
-----------------
        import java.sql.*;

        public class SQLiteJDBC3
        {
            public static void main( String args[] )
              {
                Connection c = null;
                Statement stmt = null;
                try {
                  Class.forName("org.sqlite.JDBC");
                  c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
                  c.setAutoCommit(false);
                  System.out.println("Opened database successfully");

                  stmt = c.createStatement();
                  String sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
                  "VALUES ( 'Accord LX', 'Hyundai',12000,'2007-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00' );"; 
                  stmt.executeUpdate(sql);

                  sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
                  "VALUES ( 'Honda', 'Hyundai',10000,'2010-01-01 10:00:00',1,4,4,12000,2,0,'0000-00-00 00:00:00' );"; 
                  stmt.executeUpdate(sql);

                  sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
                  "VALUES ( 'Honda', 'Hyundai',11000,'2012-01-01 10:00:00',1,4,4,1000,2,0,'0000-00-00 00:00:00' );"; 
                  
                  stmt.executeUpdate(sql);

                  sql = "INSERT INTO cars ( model,brand,price,built_date,gear,seat,wheels,miles,capacity,sold,sold_on )" +
                  "VALUES ( 'Honda Sed', 'Hyundai',13000,'2013-01-01 10:00:00',1,4,4,10000,2,0,'0000-00-00 00:00:00' );"; 
                  stmt.executeUpdate(sql);

                  stmt.close();
                  c.commit();
                  c.close();
                } catch ( Exception e ) {
                  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                  System.exit(0);
                }
                System.out.println("Records created successfully");
              }
        }

SELECT Operation ВЫБОРКА
========================
Для выборки данных воспользуемся командой SELECT.


        SELECT id, model,brand,price,built_date,gear
        FROM cars;

Этот же запрос может выглядеть так:
        SELECT *
        FROM cars;

В результате из таблицы будут извлечены все строки. Результат может выглядеть без разграничения по колонкам и без заголовка. 

Чтобы это исправить выполняем:

        .show

Для отображения шапки введите .headers ON.

Для отображения колонок выполните команду .mode column.

Выполняем SELECT запрос ещё раз.

вид отображения можно изменить, воспользовавшись мета командой .mode.

class SQLiteJDBC4
-----------------
        import java.sql.*;
        public class SQLiteJDBC4 {

        public static void main( String args[]) 
            {
               Connection c = null;
                  Statement stmt = null;
                  try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
                    c.setAutoCommit(false);
                    System.out.println("Opened database successfully");

                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "SELECT * FROM cars;" );
                    while ( rs.next() ) {
                       int id = rs.getInt("id");
                       String  model = rs.getString("model");
                       String built_date  = rs.getString("built_date");
                       String  brand = rs.getString("brand");
                       float price = rs.getFloat("price");
                       System.out.println( "ID = " + id );
                       System.out.println( "model = " + model );
                       System.out.println( "built_date = " + built_date );
                       System.out.println( "brand = " + brand );
                       System.out.println( "price = " + price );
                       System.out.println();
                    }
                    rs.close();
                    stmt.close();
                    c.close();
                  } catch ( Exception e ) {
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    System.exit(0);
                  }
                  System.out.println("Operation done successfully");
            }
        }


UPDATE Operation ОБНОВЛЕНИЕ
---------------------------

        UPDATE cars
        SET price = 14000
        WHERE id = 2;

В результате запись будет изменена.

        UPDATE cars
        SET price = 14000
        WHERE model = 'Honda';

Значение в колонке model может быть не уникально, так что в результате работы команды может быть затронуто более одной строки. Для изменения какой-то конкретной строки следует её отследить по полю id. Мы его определили как PRIMARY KEY, что обеспечивает уникальность значения.


        import java.sql.*;

        public class SQLiteJDBC5
        {
          public static void main( String args[] )
          {
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

              ResultSet rs = stmt.executeQuery( "SELECT * FROM cars;" );
              while ( rs.next() ) {
                               int id = rs.getInt("id");
                               String  model = rs.getString("model");
                               String built_date  = rs.getString("built_date");
                               String  brand = rs.getString("brand");
                               float price = rs.getFloat("price");
                               System.out.println( "ID = " + id );
                               System.out.println( "model = " + model );
                               System.out.println( "built_date = " + built_date );
                               System.out.println( "brand = " + brand );
                               System.out.println( "price = " + price );
                               System.out.println();
                            }
              rs.close();
              stmt.close();
              c.close();
            } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
            }
            System.out.println("Operation done successfully");
          }
        }

DELETE Operation УДАЛЕНИЕ
--------------------------
Для выполнения команды DELETE нужно так же указать условие.


        DELETE FROM cars
        WHERE post_id = 5;

Для удаления cars 'Honda' OR 'Homer'; выполним:


        DELETE FROM cars
        WHERE model = 'Honda' OR model = 'Homer';

class SQLiteJDBC
----------------
        import java.sql.*;

        public class SQLiteJDBC
        {
          public static void main( String args[] )
          {
            Connection c = null;
            Statement stmt = null;
            try {
              Class.forName("org.sqlite.JDBC");
              c = DriverManager.getConnection("jdbc:sqlite:vehicle.db");
              c.setAutoCommit(false);
              System.out.println("Opened database successfully");

              stmt = c.createStatement();
              String sql = "DELETE from cars where id=2;";
              stmt.executeUpdate(sql);
              c.commit();

              ResultSet rs = stmt.executeQuery( "SELECT * FROM cars;" );
              while ( rs.next() ) {
                               int id = rs.getInt("id");
                               String  model = rs.getString("model");
                               String built_date  = rs.getString("built_date");
                               String  brand = rs.getString("brand");
                               float price = rs.getFloat("price");
                               System.out.println( "ID = " + id );
                               System.out.println( "model = " + model );
                               System.out.println( "built_date = " + built_date );
                               System.out.println( "brand = " + brand );
                               System.out.println( "price = " + price );
                               System.out.println();
                            }
              rs.close();
              stmt.close();
              c.close();
            } catch ( Exception e ) {
              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
              System.exit(0);
            }
            System.out.println("Operation done successfully");
          }
        }

class SQLiteJDBC6
-----------------

        import java.sql.Connection;
        import java.sql.Statement;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.DatabaseMetaData;

        public class SQLiteJDBC6
        {
            public static void main (String[] args) throws Exception
            {
                // register the driver 
                String sDriverName = "org.sqlite.JDBC";
                Class.forName(sDriverName);
         
                // now we set up a set of fairly basic string variables to use in the body of the code proper
                String sTempDb = "test.db";
                String sJdbc = "jdbc:sqlite";
                String sDbUrl = sJdbc + ":" + sTempDb;
                // which will produce a legitimate Url for SqlLite JDBC :
                // jdbc:sqlite:hello.db
                int iTimeout = 30;
                String sMakeTable = "CREATE TABLE dummy (id numeric, response text)";
                String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
                String sMakeSelect = "SELECT response from dummy";
         
                // create a database connection
                Connection conn = DriverManager.getConnection(sDbUrl);
                try {
                    Statement stmt = conn.createStatement();
                    try {
                        stmt.setQueryTimeout(iTimeout);
                        stmt.executeUpdate( sMakeTable );
                        stmt.executeUpdate( sMakeInsert );
                        ResultSet rs = stmt.executeQuery(sMakeSelect);
                        try {
                            while(rs.next())
                                {
                                    String sResult = rs.getString("response");
                                    System.out.println(sResult);
                                }
                        } finally {
                            try { rs.close(); } catch (Exception ignore) {}
                        }
                    } finally {
                        try { stmt.close(); } catch (Exception ignore) {}
                    }
                } finally {
                    try { conn.close(); } catch (Exception ignore) {}
                }
            }
        }


class SQLiteJDBC7
-----------------
            import java.sql.Connection;
            import java.sql.DriverManager;
            import java.sql.ResultSet;
            import java.sql.SQLException;
            import java.sql.Statement;


            class conn {
                public static Connection conn;
                public static Statement statmt;
                public static ResultSet resSet;
                
                // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
                public static void Conn() throws ClassNotFoundException, SQLException 
                   {
                       conn = null;
                       Class.forName("org.sqlite.JDBC");
                       conn = DriverManager.getConnection("jdbc:sqlite:test.db");
                       
                       System.out.println("База Подключена!");
                   }
                
                // --------Создание таблицы--------
                public static void CreateDB() throws ClassNotFoundException, SQLException
                   {
                    statmt = conn.createStatement();
                    statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
                    
                    System.out.println("Таблица создана или уже существует.");
                   }
                
                // --------Заполнение таблицы--------
                public static void WriteDB() throws SQLException
                {
                       statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
                       statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
                       statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");
                      
                       System.out.println("Таблица заполнена");
                }
                
                // -------- Вывод таблицы--------
                public static void ReadDB() throws ClassNotFoundException, SQLException
                   {
                    resSet = statmt.executeQuery("SELECT * FROM users");
                    
                    while(resSet.next())
                    {
                        int id = resSet.getInt("id");
                        String  name = resSet.getString("name");
                        String  phone = resSet.getString("phone");
                         System.out.println( "ID = " + id );
                         System.out.println( "name = " + name );
                         System.out.println( "phone = " + phone );
                         System.out.println();
                    }   
                    
                    System.out.println("Таблица выведена");
                    }
                
                    // --------Закрытие--------
                    public static void CloseDB() throws ClassNotFoundException, SQLException
                       {
                      statmt.close();
                        resSet.close();
                      conn.close();
                       
                        System.out.println("Соединения закрыты");
                       }

            }
            public class SQLiteJDBC7
            {
                public static void main(String[] args) throws ClassNotFoundException, SQLException {
                    conn.Conn();
                    conn.CreateDB();
                    conn.WriteDB();
                    conn.ReadDB();
                    conn.CloseDB();
                  }
            }


