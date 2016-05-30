import java.util.Vector;
import javax.swing.table.AbstractTableModel;

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
