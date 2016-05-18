import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow19  extends JFrame {
  
  SimpleWindow19(){
    super("Пример использования JSlider");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 50, 150, 70);
    slider.setMajorTickSpacing(20);
    slider.setMinorTickSpacing(5);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setSnapToTicks(true);
    JPanel panel = new JPanel();
    panel.add(slider);
    setContentPane(panel);
    pack();
  }

}
