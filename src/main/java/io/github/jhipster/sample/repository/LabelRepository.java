package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Label;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Neo4j repository for the Label entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LabelRepository extends Neo4jRepository<Label, String> {
}
