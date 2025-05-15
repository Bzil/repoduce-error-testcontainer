package bz.test.readonly;

import bz.test.readonly.docker.DockerComposePostgres;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration(proxyBeanMethods = false)
public class TestConfig {

    @Bean
    public DockerComposePostgres dockerizedPostgres(
            @Value("classpath:/docker/postgres-replica.yml") Resource dockerComposeFile
    ) throws Exception {
        return new DockerComposePostgres(dockerComposeFile);
    }
}
