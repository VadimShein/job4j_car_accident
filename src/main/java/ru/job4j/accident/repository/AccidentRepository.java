package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("select ac from Accident ac join fetch ac.type join fetch ac.rules where ac.id = ?1")
    Accident get(int id);

    @Query("select distinct ac from Accident ac join fetch ac.type join fetch ac.rules")
    Collection<Accident> getAll();

    @Query("select act from AccidentType act where act.id = ?1")
    AccidentType getType(int id);
    @Query("select ac_t from AccidentType ac_t")
    Collection<AccidentType> getAllTypes();

    @Query("select r from Rule r where r.id = ?1")
    Rule getRule(int id);
    @Query("select r from Rule r")
    Collection<Rule> getAllRules();
}