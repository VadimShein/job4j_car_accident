package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Repository
public class AccidentMemStore implements AccidentStore {
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMemStore() {
       add(new Accident(1, "crash", "N001", "address1",
                new Date(System.currentTimeMillis()), "user1", "in progress"));
        add(new Accident(2, "crash", "N002", "address2",
                new Date(System.currentTimeMillis()), "user2", "in progress"));
        add(new Accident(3, "crash", "N003", "address3",
                new Date(System.currentTimeMillis()), "user3", "in progress"));
    }

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }
}
