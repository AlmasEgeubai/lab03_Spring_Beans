package eak_04;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            lNotebookDAO notebookDAO = (lNotebookDAO) context.getBean("notebookDAO"); // Загрузка бина доступа к таблице 

           notebookDAO.deleteAll(); // Удаление всех записей
          
          
            notebook notebook = new notebook("Марат", "Алмата", 658575); // Создание нового объекта таблицы  
            notebookDAO.insert(notebook); // Вставить новый объект (запись) в таблицу 

            notebookDAO.insert(new notebook("Жанибек", "Павлодар", 585848)); // Вставить новый объект (запись) в таблицу 
            notebookDAO.insert(new notebook("Меирбек", "Кокшетау", 954523));
            notebookDAO.insert(new notebook("Игорь", "Астана", 585546));

            System.out.println("\nИзначальное состояние БД:");

            List<notebook> list = notebookDAO.selectAll();
            for (notebook myNotebook : list) {
                System.out.println(myNotebook.getName() + " " + myNotebook.getCity() + " " + myNotebook.getNum() + " " );
            }

            System.out.println("\nВывод записи, в которой номер 585848");
            notebook notebook1 = notebookDAO.findByNum(585848); // Поиск записи по 
            System.out.println(notebook1 != null ? notebook1 : "Нет данных"); // Вывод на экран найденной записи

            notebookDAO.deleteByCity("Кокшетау");
            
            System.out.println("\nУдаление записей по городу 'Кокшетау'");
            
           notebookDAO.delete("Игорь", "Астана");
           
             System.out.println("\nУдаление записей по имени 'Игорь' и городе 'Астана'");
             
            System.out.println("\nВывод записи, содержащей город 'Алмата'");
            List<notebook> City = notebookDAO.findByCity("Алмата"); // Поиск записей по городу
            System.out.println(City != null ? City : "Нет данных");

            System.out.println("\nИзменение значения  в таблице с 'Алмата' на 'Павлодар'");
            notebookDAO.update( "Алмата", "Павлодар");

          
            
            
            System.out.println("\nКонечное состояние БД:");

            List<notebook> list1 = notebookDAO.selectAll();
            for (notebook myNotebook : list1) {
                System.out.println(myNotebook.getName() + " " + myNotebook.getCity() + " " + myNotebook.getNum());
           
            }
            
            System.out.println("\nВывод записей с именем 'Марат' и городом 'Павлодар':");
            list = notebookDAO.select("Марат", "Павлодар");
            
            for (notebook myNotebook : list) {
                System.out.println(myNotebook.getName() + " " + myNotebook.getCity()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
        }
    }
