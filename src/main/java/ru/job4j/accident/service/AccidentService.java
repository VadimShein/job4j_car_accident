package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;
import java.util.HashSet;

@Service
public class AccidentService {
    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public void add(Accident accident, String[] ids) {
        accident.setType(getType(accident.getType().getId()));
        HashSet<Rule> rules = new HashSet<>();
        for (String rId : ids) {
            rules.add(getRule(Integer.parseInt(rId)));
        }
        accident.setRules(rules);
        store.add(accident);
    }

    public Accident get(int id) {
        return store.get(id);
    }

    public Collection<Accident> getAll() {
        return store.getAll();
    }


    public AccidentType getType(int id) {
        return store.getType(id);
    }

    public Collection<AccidentType> getAllTypes() {
        return store.getAllTypes();
    }


    public Rule getRule(int id) {
        return store.getRule(id);
    }

    public Collection<Rule> getAllRules() {
        return store.getAllRules();
    }
}
