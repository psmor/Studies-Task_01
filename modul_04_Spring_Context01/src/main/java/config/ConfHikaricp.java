package config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

// Пул соединений в виде Spring бина
@Configuration
public class ConfHikaricp {
    @Value("jdbc:postgresql://localhost:5432/postgres")
    private String url;
    @Value("postgres")
    private String userName;
    @Value("postgres")
    private String password;
    @Value("org.postgresql.Driver")
    private String driverClassName;
    @Value("10")
    private int maxPoolSize;

    @Bean
    public DataSource hikariDataSource() throws SQLException {
        // получаем параметры коннекта
        HikariDataSource dataSource = new HikariDataSource(); // hikari pool
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setConnectionTimeout(20000); // 20 сек.
        dataSource.setMinimumIdle(5);           // Кол-во открытых соединений изначально
        dataSource.setMaximumPoolSize(10);      // Общее допустимое соединений в пуле
        dataSource.setMaxLifetime(900000);      // 15 мин. Max впемя жизни соединения в пуле

        return dataSource;
    }

//    @Bean
//    public Connection getConnection() throws SQLException {
//        HikariDataSource dataSource = hikariDataSource();
//
//        return dataSource.getConnection();  // коннект
//    }


}
