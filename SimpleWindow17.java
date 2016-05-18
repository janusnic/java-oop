import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow17  extends JFrame {
  
  SimpleWindow17(){
    super("Пример использования JToolBar");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JTextArea textArea = new JTextArea(5, 20);
    getContentPane().add(textArea);
    JToolBar toolBar = new JToolBar("Инструментальная панель");
    toolBar.add(new JButton("Кнопка 1"));
    toolBar.add(new JButton("Кнопка 2"));
    toolBar.addSeparator();
    toolBar.add(new JButton("Кнопка 3"));
    getContentPane().add(toolBar, BorderLayout.NORTH);
    pack();
  }

}