package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMemStore;
import ru.job4j.accident.repository.AccidentStore;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentStore store;

    public AccidentService(AccidentMemStore store) {
        this.store = store;
    }

    public void add(Accident accident) {
        store.add(accident);
    }

    public Accident get(int id) {
        return store.get(id);
    }

    public Collection<Accident> getAll() {
        return store.getAll();
    }
}
