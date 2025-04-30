package psmor.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import psmor.jpa.repository.UserRepository;
import psmor.jpa.srtuct.User;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class UserService implements CommandLineRunner {
    UserRepository userRepository;
    ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        User user;

        // Удалим все данные
        deleteAll();

        // Вставим 2-е записи
        insert("Боб");
        insert("Пол");
        insert("Док");

        //Изменим апись с именем Пол
        user = selectUsername("Пол");       // Получим ID с именем "Пол"
        update(user.getId(), "Вася");

        //Удалим запись с именем Док
        user = selectUsername("Док");       // Получим ID с именем "Пол"
        delete(user.getId());

        //Выведем все записи
        System.out.println(toJson(selectAll())); //System.out.println(objectMapper.writeValueAsString(selectAll()));

    }

    public void insert(String name) {
        userRepository.save(new User(null, name));
    }
    public List<User> selectAll() {
        List<User> users = userRepository.findAll();
        //List<User> users = userRepository.findAll().forEach(item -> log.info("item: {}", item));
        return users;
    }

    public User selectId(Long id) {
        Optional<User> res = userRepository.findById(id);
        User user = null;
        if ( res.isPresent() ){
            user = res.get();  // Так можно делать? Ниже в selectUsername() сделал иначе.
        } else {
            log.info("Пользователь с id="+id+" не найден");
        }
        return user;
    }

    public User selectUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public void update(Long id, String name) {
        User user = selectId(id);
        if (user != null) {
            user.setUsername(name);
            userRepository.save(user);
        } else {
            log.info("Пользователь с id="+id+" не найден");
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    // Получаем JSON
    public String toJson (User user) throws JsonProcessingException {
        return (objectMapper.writeValueAsString(user));
    }
    public String toJson (List<User> user) throws JsonProcessingException {
        return (objectMapper.writeValueAsString(user));
    }

}
