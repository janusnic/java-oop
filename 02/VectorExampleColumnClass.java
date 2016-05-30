
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
