package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfObjectMapper {
    @Bean(name = "object_mapper")       //  Для его использования нужно высвать:
                                        // @Qualifier("object_mapper") ObjectMapper objectMapper;
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE); // при выводе в JSON строку оборачивает в имя класса
        return objectMapper;
    }


//    @Primary           // в коде ObjectMapper, если не выбрано конкретное имя всегда будет этот
//                       // ObjectMapper objectMapper;
//    @Bean
//    public ObjectMapper objectMapperOrigin(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper;
//    }

}
