package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

@Transactional
public interface AccidentRepository extends CrudRepository<Accident, Long> {
    @Query("select ac from Accident ac join fetch ac.type join fetch ac.rules where ac.id = ?1")
    Accident get(int id);

    @Query("select distinct ac from Accident ac join fetch ac.type join fetch ac.rules")
    Collection<Accident> getAll();
}