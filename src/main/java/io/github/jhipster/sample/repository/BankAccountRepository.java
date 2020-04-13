package io.github.jhipster.sample.repository;

import io.github.jhipster.sample.domain.BankAccount;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data Neo4j repository for the BankAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountRepository extends Neo4jRepository<BankAccount, String> {
}
