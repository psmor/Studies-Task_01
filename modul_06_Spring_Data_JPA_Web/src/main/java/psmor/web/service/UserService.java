package psmor.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import psmor.web.dto.UserDto;
import psmor.web.dto.UserDtoResp;
import psmor.web.entity.Product;
import psmor.web.repository.UserRepository;
import psmor.web.entity.User;
import psmor.web.utils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final MappingUtils mappingUtils;

    public void insert(String name, List<Product> product) {
        userRepository.save(new User(null, name, product));
    }

    public UserDtoResp selectAllDto() {
        // Найдём пользователей
        List<User> users = userRepository.findAll();
        // Запишу лог
        log.info("JSON: "+toJson(users));
        // Собираем DTO из пользователей
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(mappingUtils.userToUserDto(user));
        }
        return new UserDtoResp(usersDto);
    }


    public User selectId(Long id) {
        // Ниже в selectUsername() сделал в одну строку. Это оставил для примера
        Optional<User> res = userRepository.findById(id);
        User user = new User();
        if ( res.isPresent() ){
            user = res.get();
        } else {
            log.info("Пользователь с id="+id+" не найден");
        }

        // Запишу лог
        log.info("JSON: "+toJson(user));

        return user;
    }

    public UserDto selectIdDto(Long id) {
        User user = selectId(id);
        return mappingUtils.userToUserDto(user);
    }

    public User selectUsername(String username){
        //return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Пользователь с именем "+username+" не найден"));
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
    public String toJson (User user) {
        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            return ("Ошибка преобразования в JSON:"+e.toString());
        }

    }
    public String toJson (List<User> user) {
        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            return ("Ошибка преобразования в JSON:"+e.toString());
        }

    }

}
