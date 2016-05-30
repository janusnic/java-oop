
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
