package eak_04;

import javax.sql.DataSource;
import java.util.List;

public interface lNotebookDAO {

    void setDataSource(DataSource ds); // Установка связи с данными

    void insert(notebook customer); // Вставка новой записи

    void append(String name, String city, int num);

    void deleteByCity(String city);

    void delete(String name, String city); // Удаление записи 

    void deleteAll(); // Удаление всех записей

    void update(String oldCity, String newCity);

    notebook findByNum(int num); // Получение записи с заданным номером 

    List<notebook> findByName(String name); // Нахождение записей с заданным городом

    List<notebook> findByCity(String city); // Нахождение записей с заданным городом
    
    List<notebook> select(String name, String city); // Выбор записей с заданным именем

    List<notebook> selectAll();
}
