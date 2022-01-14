package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface TypeRepository extends CrudRepository<AccidentType, Long>  {
    @Query("select act from AccidentType act where act.id = ?1")
    AccidentType getType(int id);
}
