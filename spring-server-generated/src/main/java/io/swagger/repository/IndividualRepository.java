package io.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Individual;

@Repository
public interface IndividualRepository extends CrudRepository<Individual, String> {

}
