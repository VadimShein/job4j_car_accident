package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Transactional
public interface RuleRepository extends CrudRepository<Rule, Long>  {
    @Query("select r from Rule r where r.id = ?1")
    Rule getRule(int id);

    @Query("select r from Rule r")
    Collection<Rule> getAllRules();
}
