package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data Neo4j repository for the Operation entity.
 */
@Repository
public interface OperationRepository extends Neo4jRepository<Operation, String> {

    @Query("MATCH (n:Operation)-[]->(m) RETURN n,m")
    Page<Operation> findAllWithEagerRelationships(Pageable pageable);

    @Query("MATCH (n:Operation {id: $id})-[]->(m) RETURN n,m")
    Optional<Operation> findOneWithEagerRelationships(String id);
}
