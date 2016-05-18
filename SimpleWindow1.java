import javax.swing.*;
public class SimpleWindow1  extends JFrame {
    SimpleWindow(){
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton newButton = new JButton();
        getContentPane().add(newButton);
        setSize(250, 100);
        }

}
