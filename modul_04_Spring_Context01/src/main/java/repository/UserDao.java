package repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import srtuct.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private final DataSource dataSource;

    // Заинжектим DataSource из Config
    public UserDao( DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //INSERT
    public void insert(Long id, String name) throws SQLException{
        String SQL_QUERY = "INSERT INTO users (id, name) VALUES (?,?)";
        Connection con = dataSource.getConnection();                // Открываем коннет
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);    // Формируем запрос
        pst.setLong(1, id);                           // В запрос добавляем переменные
        pst.setString(2, name);                       // В запрос добавляем переменные
        ResultSet rs = pst.executeQuery();                          // Выполняем
    }

    // SELECT всех записей
    public List<User> selectAll() throws SQLException {
        List<User> users = null;
        String SQL_QUERY = "SELECT * FROM users";
        Connection con = dataSource.getConnection();                // Открываем коннет
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);    // Формируем запрос
        ResultSet rs = pst.executeQuery();                          // Отправляем запрос и получаем результат

        // Соберём ответ в коллекцию
        users = new ArrayList<>();
        User user;
        while (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("Bob"));
            users.add(user);
        }
        return users;
    }

    // SELECT по id
    public List<User> selectId(Long id) throws SQLException {
        List<User> users = null;
        String SQL_QUERY = "SELECT * FROM users WHERE id = ?";
        Connection con = dataSource.getConnection();                // Открываем коннет
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);    // Формируем запрос
        pst.setLong(1, id);                           // В запрос добавляем переменные
        ResultSet rs = pst.executeQuery();                          // Получаем результат
        // Соберём ответ в коллекцию
        users = new ArrayList<>();
        User user;
        while (rs.next()) {                                             // Соберём ответ в коллекцию
            user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("Bob"));
            users.add(user);
        }

        return users;
    }

    // UPDATE
    public void update(Long id, String name) throws SQLException{
        String SQL_QUERY = "UPDATE users SET name=? WHERE id = ?";
        Connection con = dataSource.getConnection();                // Открываем коннет
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);    // Формируем запрос
        pst.setLong(1, id);                           // В запрос добавляем переменные
        pst.setString(2, name);                       // В запрос добавляем переменные
        ResultSet rs = pst.executeQuery();                          // Выполняем
    }

    // DELETE
    public void delete(Long id) throws SQLException{
        String SQL_QUERY = "DELETE FROM users WHERE id = ?";
        Connection con = dataSource.getConnection();                // Открываем коннет
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);    // Формируем запрос
        pst.setLong(1, id);                           // В запрос добавляем переменные
        ResultSet rs = pst.executeQuery();                          // Выполняем
    }
}


