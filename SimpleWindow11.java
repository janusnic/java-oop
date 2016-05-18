import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow11  extends JFrame {
  
  SimpleWindow11(){
  super("Окно с надписью");
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  JLabel label = new JLabel("Метка со значком и с надписью", new ImageIcon("1.gif"), SwingConstants.RIGHT);
  getContentPane().add(label);
  pack();
}

}