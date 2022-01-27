package app.connection;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@Data
@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxProperties {
    private File secureConnectBundle;
}
