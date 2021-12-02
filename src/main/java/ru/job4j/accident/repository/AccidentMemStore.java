package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemStore implements AccidentStore {
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMemStore() {
       add(new Accident(COUNT.incrementAndGet(), "N001", "address1", "crash",
                new Date(System.currentTimeMillis()), "user1", "Processed"));
        add(new Accident(COUNT.incrementAndGet(), "N002", "address2", "crash",
                new Date(System.currentTimeMillis()), "user2", "Processed"));
        add(new Accident(COUNT.incrementAndGet(), "N003", "address3", "crash",
                new Date(System.currentTimeMillis()), "user3", "Processed"));
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
}
