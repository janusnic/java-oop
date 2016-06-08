package vehicle;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class CarTableModel extends AbstractTableModel  {
    // Сделаем хранилище для нашего списка автомобилей

    private Vector cars;

    // Модель при создании получает список автомобилей
    public CarTableModel(Vector cars) {
        this.cars = cars;
    }

    // Количество строк равно числу записей
    public int getRowCount() {
        if (cars != null) {
            return cars.size();
        }
        return 0;
    }

    // Количество столбцов - 4
    public int getColumnCount() {
        return 4;
    }

    // Вернем наименование колонки
    public String getColumnName(int column) {
        String[] colNames = {"Модель", "Цена", "Пробег", "Дата выпуска"};
        return colNames[column];
    }

    // Возвращаем данные для определенной строки и столбца
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (cars != null) {
            // Получаем из вектора автомобиля
            Car car = (Car) cars.get(rowIndex);
            // В зависимости от колонки возвращаем модель, цена и т.д.
            switch (columnIndex) {
                case 0:
                    return car.getModel();
                case 1:
                    return car.getPrice();
                case 2:
                    return car.getMiles();
                case 3:
                    return DateFormat.getDateInstance(DateFormat.SHORT).format(
                            car.getBuiltDate());
            }
        }
        return null;
    }

    // Добавим метод, который возвращает автомобиль по номеру строки
   
    public Car getCar(int rowIndex) {
        if (cars != null) {
            if (rowIndex < cars.size() && rowIndex >= 0) {
                return (Car) cars.get(rowIndex);
            }
        }
        return null;
    }
}
