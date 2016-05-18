import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow21  extends JFrame {
  
  SimpleWindow21(){
    super("Пример с JList");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Object[] elements = new Object[] {"Колбаса", "<html><font color = red>Масло", "Сгущенное молоко"};
    JList list = new JList(elements);
    list.setVisibleRowCount(5);
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setSelectedIndices(new int[] {1,2});
    getContentPane().setLayout(new FlowLayout());
    getContentPane().add(new JScrollPane(list));
    setSize(200,150);
  }

}
