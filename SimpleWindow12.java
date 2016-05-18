import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow12  extends JFrame {
  
  SimpleWindow12(){
  super("Окно с надписью");
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  // JLabel label = new JLabel("Метка со значком и с надписью", new ImageIcon("1.gif"), SwingConstants.RIGHT);
  JLabel label = new JLabel("<html>К этой метке применено " +
      "HTML-форматирование, включая: <ul><li> <i>курсив</i>," +
      "<li><b>полужирный</b> <li><font size = +2> увеличение размера </font>" +
      "<li>маркированный список </ul>");

  getContentPane().add(label);
  pack();
}

}
