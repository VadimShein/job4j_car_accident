package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentStore {
    void add(Accident accident);
    Accident get(int id);
    Collection<Accident> getAll();
    void addType(AccidentType type);
    AccidentType getType(int id);
    Collection<AccidentType> getAllTypes();
    void addRule(Rule rule);
    Rule getRule(int id);
    Collection<Rule> getAllRules();
}
