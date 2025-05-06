package psmor.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import psmor.web.entity.Product;
import psmor.web.repository.UserRepository;
import psmor.web.entity.User;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    ObjectMapper objectMapper;

    public void insert(String name, List<Product> product) {
        userRepository.save(new User(null, name, product));
    }

    public List<User> selectAll() {
        List<User> users = userRepository.findAll();
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
