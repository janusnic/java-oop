package vehicle;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JTextField;

public class BrandDialog extends JDialog implements ActionListener {

    private static final int D_HEIGHT = 250;   // высота
    private final static int D_WIDTH = 400;   // ширина

    private JTextField brandtName = new JTextField();
    private JComboBox brandList;
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private boolean result = false;

    public BrandDialog(List<Brand> brands) {
        // Установить заголовок
        setTitle("Управление Брендами");

        // Создаем сложный layout для нашего окна
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        // Создаем переменную для установки правил размещения
        GridBagConstraints c = new GridBagConstraints();
        // Сразу задаем отступ от границ для каждого элемента
        c.insets = new Insets(5, 5, 5, 5);

        // Первый элемент - заголовок для поля выбора производителя
        JLabel l = new JLabel("Выбор производителя:");
        
        
        // После него можно будет еще помещать компоненты
        c.gridwidth = GridBagConstraints.RELATIVE;
        // Не заполняем все пространство, отведенное компоненту
        c.fill = GridBagConstraints.NONE;
        // "Привязываем" компонент к правому краю
        c.anchor = GridBagConstraints.EAST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(l, c);
        // Добавляем компонент
        getContentPane().add(l);

        // Второй элемент - список производителей
        brandList = new JComboBox(new Vector<Brand>(brands));
        // Элемент занимает всю оставшуюся ширину
        c.gridwidth = GridBagConstraints.REMAINDER;
        // Растягиваем компонент по всему пространству для него
        c.fill = GridBagConstraints.BOTH;
        // "Привязываем" его к левой части
        c.anchor = GridBagConstraints.WEST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(brandList, c);
        // Добавляем компонент
        getContentPane().add(brandList);
        
        // Третий элемент - заголовок для поля Новый производитель
        l = new JLabel("Новый производитель:");
        // После него можно будет еще помещать компоненты
        c.gridwidth = GridBagConstraints.RELATIVE;
        // Не заполняем все пространство, отведенное компоненту
        c.fill = GridBagConstraints.NONE;
        // "Привязываем" компонент к правому краю
        c.anchor = GridBagConstraints.EAST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(l, c);
        
        // Добавляем компонент
               
        getContentPane().add(l);

        // Элемент занимает всю оставшуюся ширину
        c.gridwidth = GridBagConstraints.REMAINDER;
        // Растягиваем компонент по всему пространству для него
        c.fill = GridBagConstraints.BOTH;
        // "Привязываем" его к левой части
        c.anchor = GridBagConstraints.WEST;
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(brandtName, c);
        // Добавляем компонент
        
        getContentPane().add(brandtName);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;

        btnOk.setName("OK");
        // Добавляем листенер для кнопки
        btnOk.addActionListener(this);
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(btnOk, c);
        // Добавляем компонент
        getContentPane().add(btnOk);

        btnCancel.setName("Cancel");
        // Добавляем листенер для кнопки
        btnCancel.addActionListener(this);
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(btnCancel, c);
        // Добавляем компонент
        getContentPane().add(btnCancel);

        // Устанавливаем поведение формы при закрытии - не закрывать совсем.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Получаем размеры экрана
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // А теперь просто помещаем его по центру, вычисляя координаты на основе полученной информации
        setBounds(((int) d.getWidth() - BrandDialog.D_WIDTH) / 2, ((int) d.getHeight() - BrandDialog.D_HEIGHT) / 2,
                BrandDialog.D_WIDTH, BrandDialog.D_HEIGHT);
    }

    // Возврат производителя, который установлен на форме
    public Brand getBrand() {
        if (brandList.getModel().getSize() > 0) {
            return (Brand) brandList.getSelectedItem();
        }
        return null;
    }

    // Получить результат, true - кнопка ОК, false - кнопка Cancel
    public boolean getResult() {
        return result;
    }

    // Обработка нжатия кнопок
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals("OK")) {
            result = true;
        }
        if (src.getName().equals("Cancel")) {
            result = false;
        }
        setVisible(false);
    }
}
