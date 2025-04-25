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
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // max количество подключений
    @Value("${spring.datasource.hikari.max-pool-size}")
    private int maxPoolSize;
    // Тайм-аут соединения в миллисекундах, в течение которых клиент будет ждать нового соединения из пула.
    // Он выдаст исключение, SQLExceptionесли в пуле нет доступных соединений.
    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;
    //  мmin количество бездействующих подключений, которые следует поддерживать в пуле подключений.
    @Value("${spring.datasource.hikari.min-idle}")
    private int minIdle;
    // max время, в течение которого соединение может оставаться бездействующим в пуле, прежде чем оно будет удалено.
    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;
    // max время, в течение которого соединение может оставаться в пуле, прежде чем оно будет закрыто и заменено новым.
    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    @Bean
    public DataSource dataSource() throws SQLException {
        // получаем параметры коннекта
        HikariDataSource dataSource = new HikariDataSource(); // hikari pool
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setMinimumIdle(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);

        return dataSource;
    }
}
