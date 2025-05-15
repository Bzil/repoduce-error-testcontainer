package bz.test.readonly.docker;

import org.springframework.core.io.Resource;
import org.testcontainers.containers.ComposeContainer;

import java.io.IOException;

public class DockerComposePostgres implements AutoCloseable {

    private final Resource dockerComposeFile;
    private ComposeContainer environment;

    public DockerComposePostgres(Resource dockerComposeFile) throws IOException {
        this.dockerComposeFile = dockerComposeFile;

        startImages();
    }

    private void startImages() throws IOException {
        environment = new ComposeContainer(dockerComposeFile.getFile())
                .withExposedService("postgres-primary", 25432)
                .withExposedService("postgres-standby", 35432);

        environment.start();
    }

    @Override
    public void close() {
    }
}
