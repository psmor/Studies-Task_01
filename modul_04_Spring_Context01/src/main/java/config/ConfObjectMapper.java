package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfObjectMapper {
    @Bean       //  Для его использования нужно высвать:
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE); // при выводе в JSON строку оборачивает в имя класса
        return objectMapper;
    }
}
