package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Long>  {
    @Query("select r from Rule r where r.id = ?1")
    Rule getRule(int id);
}
