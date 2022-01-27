package app.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

/*    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxProperties dataStaxProperties) {
        Path bundle = dataStaxProperties.getSecureConnectBundle().toPath();
//        return cqlSessionBuilder -> cqlSessionBuilder.withCloudSecureConnectBundle(bundle);
        return cqlSessionBuilder -> cqlSessionBuilder.withKeyspace(keySpace);
    }*/

    @Bean
    public CqlSession session() {
        return CqlSession.builder().withKeyspace(keySpace).build();
    }

}
