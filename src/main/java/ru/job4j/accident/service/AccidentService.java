package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import java.util.Collection;
import java.util.HashSet;

@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final TypeRepository typeRepository;

    public AccidentService(AccidentRepository accidentRepository, RuleRepository ruleRepository,
                           TypeRepository typeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    @Transactional
    public void add(Accident accident, String[] ids) {
        accident.setType(getType(accident.getType().getId()));
        HashSet<Rule> rules = new HashSet<>();
        for (String rId : ids) {
            rules.add(getRule(Integer.parseInt(rId)));
        }
        accident.setRules(rules);
        accidentRepository.save(accident);
    }

    public Accident get(int id) {
        return accidentRepository.get(id);
    }

    public Collection<Accident> getAll() {
        return  accidentRepository.getAll();
    }

    public AccidentType getType(int id) {
        return typeRepository.getType(id);
    }

    public Iterable<AccidentType> getAllTypes() {
        return typeRepository.findAll();
    }

    public Rule getRule(int id) {
        return ruleRepository.getRule(id);
    }

    public Iterable<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
