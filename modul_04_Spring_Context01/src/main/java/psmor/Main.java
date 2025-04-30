package psmor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import psmor.service.UserService;
import psmor.srtuct.User;

import java.sql.SQLException;
import java.util.List;

@ComponentScan // Сканирует все текущие и дочерние каталоги и ищет классы отмеченные Бинами
public class Main {
    public static void main(String[] args) throws SQLException, JsonProcessingException {
        // Создаём Spring Coontext
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);   // Падает в BeanCreationException

        System.out.println("Старт Spring Context");
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService = context.getBean(UserService.class); // Работаем с контекстом

        userService.deleteAll();

        // INSERT
        userService.insert( 1L,"Боб");
        userService.insert( 2L,"Пол");

        // SELCT ALL
        List<User> users = userService.selectAll();
        for(User u : users){
            //System.out.println("1");
            System.out.println(objectMapper.writeValueAsString(u));
            //System.out.println("2");
        }

        // SELECT по id
        User user = userService.selectId(2L);
        System.out.println(objectMapper.writeValueAsString(user));

        // UPDATE
        userService.update(2L, "Бил");

        // DELETE
        userService.delete(1L);

        // SELECT ALL
        for(User u : users){
            System.out.println(objectMapper.writeValueAsString(u));
        }

        context.close();

        System.out.println("Стоп Spring Context");
    }
}
