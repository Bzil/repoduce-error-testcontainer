package bz.test.readonly.docker;

import org.springframework.core.io.Resource;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

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
                .withExposedService("postgres-primary", 5432, Wait.forLogMessage(".*database system is ready to accept connections.*", 2))
                .withExposedService("postgres-standby", 5432, Wait.forLogMessage(".*database system is ready to accept read-only connections.*", 1));

        environment.start();
    }

    @Override
    public void close() {
    }
}
