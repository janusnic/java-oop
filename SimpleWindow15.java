import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SimpleWindow15  extends JFrame {
  
  SimpleWindow15(){
    super("Пример текстовых компонентов");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JTextField textField = new JTextField("Текстовое поле", 20);
    textField.setCaretColor(Color.RED);
    textField.setHorizontalAlignment(JTextField.RIGHT);
    JPasswordField passwordField = new JPasswordField(20);
    passwordField.setEchoChar('$');
    passwordField.setText("пароль");
    JTextArea textArea = new JTextArea(5, 20);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    for (int i = 0; i <= 20; i++)
    textArea.append("Область для ввода текстового содержимого ");
    getContentPane().add(textField, BorderLayout.NORTH);
    getContentPane().add(textArea);
    getContentPane().add(passwordField, BorderLayout.SOUTH);
    pack();
}

}