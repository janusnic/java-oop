import java.util.Scanner;
public class Main {
       /*
       В городе N есть большой склад на котором существует 50000 различных полок. 
       Для удобства работников руководство склада решило заказать для каждой полки 
       табличку с номером от 00001 до 50000 в местной типографии, но когда таблички 
       напечатали, оказалось что печатный станок из-за неисправности не печатал цифру 2, 
       поэтому все таблички, в номерах которых содержалась одна или более двойка (например, 
       00002 или 20202) — надо перепечатывать. Напишите программу, которая подсчитает 
       сколько всего таких ошибочных табличек оказалось в бракованной партии.
        */

   public static void main(String[] args){
          //Создадим переменную, в которую будем заносить количество бракованных табличек
          int b=0;
          //С помощью простого цикла переберем все номера табличек
          for(int i=2;i<=50000;i++){
              //Для того, чтобы найти табличку с цифрой 2 преобразуем номер таблички в String. При помощи метода indexOf проверим наличие в строке символа 2 и если такой символ присутствует - увеличиваем счетчик на единицу
              String s=String.valueOf(i);
              if(s.indexOf('2')!=-1){
                  b++;
              }
          }
          System.out.println("Всего бракованных табличек - "+b);
      }
   }