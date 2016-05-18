import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow10  extends JFrame {
  

  SimpleWindow10(){
    super("Пробное окно");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2,3,5,10));
    panel.add(createPanel(new TitledBorder("Рамка с заголовком"), "TitledBorder"));
    panel.add(createPanel(new EtchedBorder(), "EtchedBorder"));
    panel.add(createPanel(new BevelBorder(BevelBorder.LOWERED), "BevelBorder"));
    panel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "SoftBevelBorder"));
    panel.add(createPanel(new LineBorder(Color.ORANGE, 4), "LineBorder"));
    panel.add(createPanel(new MatteBorder(new ImageIcon("1.gif")), "MatteBorder"));
    setContentPane(panel);
    pack();
    }
  
  private JPanel createPanel(Border border, String text) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(new JButton(text));
    panel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
    return panel;
    }

}