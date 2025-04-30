package psmor.service;

import org.springframework.stereotype.Service;
import psmor.repository.UserDao;
import psmor.srtuct.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@Service
public class UserService {
    DataSource dataSource;
    UserDao userDao;

    public UserService(DataSource dataSource) throws SQLException {  // Заинжектим DataSource из Config
        this.dataSource = dataSource;
        this.userDao = new UserDao(dataSource);
    }

    public void insert(Long id, String name) {
        try { userDao.insert(id, name);
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: "+e);
        }
    }

    public List<User> selectAll() {
        try { List<User> users  = userDao.selectAll();
              return users;
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: "+e);
        }
    }

    public User selectId(Long id) throws SQLException {

        try {
            List<User> users  = userDao.selectId(id);
            int i = 0;
            User user = null;
            for(User usr : users){
                i++;
                user = usr;
            }
            if (i == 0) {
                throw new IllegalArgumentException("Отсутствует пользователь с id="+id);
            } else if (i > 1) {
                throw new IllegalArgumentException("Найдено более 1-го пользователя с id="+id);
            }
            return user;
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: " + e);
        }
    }

    public void update(Long id, String name) {
        try { userDao.update(id, name);
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: "+e);
        }
    }

    public void delete(Long id) {
        try { userDao.delete(id);
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: "+e);
        }
    }

    public void deleteAll() {
        try { userDao.deleteAll();
        } catch ( SQLException e) {
            throw new IllegalArgumentException("Ошибка при обращении к БД: "+e);
        }
    }

}
