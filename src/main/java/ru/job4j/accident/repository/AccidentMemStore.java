package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemStore implements AccidentStore {
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final HashMap<Integer, Rule> rules = new HashMap<>();

    public AccidentMemStore() {
        addType(new AccidentType(1, "ДТП двух машин"));
        addType(new AccidentType(2, "ДТП машины и человека"));
        addType(new AccidentType(3, "ДТП машины и животного"));
        addType(new AccidentType(4, "ДТП машины и ЖД транспорта"));

        addRule((new Rule(1, "статья 1")));
        addRule((new Rule(2, "статья 2")));
        addRule((new Rule(3, "статья 3")));

        add(new Accident(0, new AccidentType(1, "ДТП двух машин"),
                Set.of(new Rule(1, "статья 1"), new Rule(2, "статья 2")),
                "N001", "address1", "crash", "user1", "Processed"));

        add(new Accident(0, new AccidentType(2, "ДТП машины и человека"),
                Set.of(new Rule(2, "статья 2")),
                "N002", "address2", "crash",  "user2", "Processed"));

        add(new Accident(0, new AccidentType(4, "ДТП машины и ЖД транспорта"),
                Set.of(new Rule(3, "статья 3")),
                "N003", "address3", "crash", "user3", "Processed"));
    }

    public void add(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(COUNT.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public void addType(AccidentType type) {
        accidentTypes.put(type.getId(), type);
    }

    public AccidentType getType(int id) {
        return accidentTypes.get(id);
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentTypes.values();
    }

    public void addRule(Rule rule) {
        rules.put(rule.getId(), rule);
    }

    public Rule getRule(int id) {
        return rules.get(id);
    }

    public Collection<Rule> getAllRules() {
        return rules.values();
    }
}
