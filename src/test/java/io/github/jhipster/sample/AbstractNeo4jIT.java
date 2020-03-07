package io.github.jhipster.sample;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

import java.util.concurrent.atomic.AtomicBoolean;

public class AbstractNeo4jIT implements BeforeAllCallback {

    private static AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {
            GenericContainer neo4jcontainer =
                new GenericContainer("neo4j:4.0")
                    .withExposedPorts(7687)
                    .withEnv("NEO4J_AUTH", "none");
            neo4jcontainer.start();
            System.setProperty(
                "org.neo4j.driver.uri",
                "bolt://" + neo4jcontainer.getContainerIpAddress() + ":" + neo4jcontainer.getMappedPort(7687)
            );
            started.set(true);
        }
    }
}
