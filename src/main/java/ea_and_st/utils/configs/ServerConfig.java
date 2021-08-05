package ea_and_st.utils.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:server.properties")
@ConfigurationProperties(prefix = "server")
@Data
public class ServerConfig {
    private String host;
}
