package io.github.jhipster.sample;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AbstractNeo4jIT {

    @Container
    private static Neo4jContainer neo4jContainer = new Neo4jContainer().withoutAuthentication();

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("org.neo4j.driver.uri", "bolt://" + neo4jContainer.getContainerIpAddress() + ":" + neo4jContainer.getMappedPort(7687));
    }
}
