package eak_04;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.List;

public class notebookDAO implements lNotebookDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(notebook customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO NOTEBOOK (name, city, num) VALUES(?, ?, ?)",
                new Object[]{customer.getName(), customer.getCity(), customer.getNum()});
    }

    @Override
    public void append(String name, String сity, int num) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO NOTEBOOK (name, city, num) VALUES(?, ?, ?)", new Object[]{name, сity, num});
    }

    @Override
    public void deleteByCity(String city) {  // Реализация удаления записей по прогнозу
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM NOTEBOOK WHERE city LIKE ?", new Object[]{'%' + city + '%'});
    }

    @Override

    public void delete(final String name, final String city) {  // Реализация удаления записей с указанным именем и гордом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE FROM NOTEBOOK WHERE name = ? AND city = ?", new Object[]{name, city});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from NOTEBOOK");
    }

    
    
    @Override
    public void update(String oldCity, String newCity) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE NOTEBOOK SET city = ? WHERE city = ?", new Object[]{newCity, oldCity});
    }

    @Override
    public notebook findByNum(int num) { // Реализация поиска записи с заданным номером
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<notebook> notebook = select.query("SELECT * FROM NOTEBOOK WHERE num = ?",
                new Object[]{num}, new NotebookRowMapper());
        return notebook.size() > 0 ? notebook.get(0) : null;
    }

    @Override
    public List<notebook> findByName(String name) {  // Реализация поиска записей по имени
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM NOTEBOOK WHERE name LIKE ?";
        List<notebook> Name = select.query(sql, new Object[]{'%' + name + '%'}, new NotebookRowMapper());
        return Name;
    }
    
    @Override
    public List<notebook> findByCity(String city) {  // Реализация поиска записей по городу
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM NOTEBOOK WHERE city LIKE ?";
        List<notebook> City = select.query(sql, new Object[]{'%' + city + '%'}, new NotebookRowMapper());
        return City;
    }

    @Override
    public List<notebook> select(String name, String city) {  // xsxs
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM NOTEBOOK WHERE name = ? AND city= ?",
                new Object[]{name, city}, new NotebookRowMapper());
    }

    @Override
    public List<notebook> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from NOTEBOOK", new NotebookRowMapper());
    }
}
