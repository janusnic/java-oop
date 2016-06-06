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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JTextField;

public class BrandDialog extends JDialog implements ActionListener {

    private static final int D_HEIGHT = 250;   // высота
    private final static int D_WIDTH = 500;   // ширина
    private int brandId = 0;
    private JTextField brandName = new JTextField();
    private JComboBox brandList;
    private JButton btnOk = new JButton("OK");
    private JButton btnNew = new JButton("New");
    private JButton btnCancel = new JButton("Cancel");
    private boolean result = false;
    private View owner;

    public BrandDialog(List<Brand> brands, View owner) {
        // Установить заголовок
        this.owner = owner;
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
        gbl.setConstraints(brandName, c);
        // Добавляем компонент
        
        getContentPane().add(brandName);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;
        
        btnNew.setName("New");
        // Добавляем листенер для кнопки
        btnNew.addActionListener(this);
        // Устанавливаем это правило для нашего компонета
        gbl.setConstraints(btnNew, c);
        // Добавляем компонент
        getContentPane().add(btnNew);

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
    
    public Brand insertBrand() {
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setNameBrand(brandName.getText());
        
        return brand;
    }

    // Обработка нжатия кнопок
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        // Добавляем brand, но не закрываем окно
        // Здесь мы не будем вызывать в отдельном потоке сохранение.
        // Оно не занимаем много времени и лишние действия здесь не оправданы
        if (src.getName().equals("New")) {
            result = true;
            try {
                //Db.getInstance().insertBrand(getBrand());
                Db.getInstance().insertBrand(insertBrand());
                owner.reloadBrands();
                brandName.setText("");
                
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }
            return;
        }
        if (src.getName().equals("OK")) {
            result = true;
        }
        if (src.getName().equals("Cancel")) {
            result = false;
        }
        setVisible(false);
    }
}
