package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.*;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public void add(Accident accident, String[] ids) {
        accident.setType(getType(accident.getType().getId()));
        if (accident.getId() == 0) {
            save(accident, ids);
        } else {
            update(accident, ids);
        }
    }

    private void save(Accident accident, String[] ids) {
        KeyHolder newId = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(
                                    "insert into accident (carnumber, address, description, created, author, status, type_id) "
                                            + "values (?, ?, ?, ?, ?, ?, ?)", new String[] {"id"});
                    ps.setString(1, accident.getCarNumber());
                    ps.setString(2, accident.getAddress());
                    ps.setString(3, accident.getDescription());
                    ps.setTimestamp(4, new Timestamp(accident.getCreated().getTime()));
                    ps.setString(5, accident.getAuthor());
                    ps.setString(6, accident.getStatus());
                    ps.setInt(7, accident.getType().getId());
                    return ps;
                    }, newId);
        for (String rId : ids) {
            jdbc.update("insert into accident_rules (accident_id, rules_id) values (?, ?)",
                    newId.getKey(), Integer.parseInt(rId));
        }
    }

    private void update(Accident accident, String[] ids) {
        jdbc.update("update accident set carnumber=?, address=?, description=?, created=?, author=?, "
                        + "status=?, type_id=? where id=?",
                accident.getCarNumber(), accident.getAddress(), accident.getDescription(),
                new Timestamp(accident.getCreated().getTime()), accident.getAuthor(), accident.getStatus(), accident.getType().getId(), accident.getId());
        jdbc.update("delete from accident_rules where accident_id=?", accident.getId());
        for (String rId : ids) {
            jdbc.update("insert into accident_rules (accident_id, rules_id) values (?, ?)",
                    accident.getId(), Integer.parseInt(rId));
        }
    }

    public Accident get(int id) {
        return jdbc.queryForObject("select ac.*, ac_t.type_name, r.* from accident ac "
                        + "inner join types ac_t on ac.type_id=ac_t.type_id "
                        + "left outer join accident_rules ac_r on ac.id=ac_r.accident_id "
                        + "left outer join rules r on ac_r.rules_id=r.rule_id where ac.id=?", new Object[]{id},
                (rs, row) -> findAccident(rs, new ArrayList<>()).get(0));
    }

    public List<Accident> getAll() {
        List<Accident> list = new ArrayList<>();
        jdbc.query("select ac.*, ac_t.type_name, r.* from accident ac "
                        + "inner join types ac_t on ac.type_id=ac_t.type_id "
                        + "left outer join accident_rules ac_r on ac.id=ac_r.accident_id "
                        + "left outer join rules r on ac_r.rules_id=r.rule_id order by ac.id",
                (rs, row) -> findAccident(rs, list));
        return list;
    }

    private List<Accident> findAccident(ResultSet rs, List<Accident> list) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setCarNumber(rs.getString("carNumber"));
        accident.setAddress(rs.getString("address"));
        accident.setDescription(rs.getString("description"));
        accident.setCreated(rs.getTimestamp("created"));
        accident.setAuthor(rs.getString("author"));
        accident.setStatus(rs.getString("status"));

        AccidentType type = new AccidentType();
        type.setId(rs.getInt("type_id"));
        type.setName(rs.getString("type_name"));
        accident.setType(type);

        Set<Rule> rules = new HashSet<>();
        rules.add(new Rule(rs.getInt("rule_id"), rs.getString("rule_name")));

        while (rs.next()) {
            if (rs.getInt("id") == accident.getId()) {
                rules.add(new Rule(rs.getInt("rule_id"), rs.getString("rule_name")));
            } else {
                findAccident(rs, list);
            }
        }
        accident.setRules(rules);
        list.add(accident);
        return list;
    }

    public void addType(AccidentType type) {
        jdbc.update("insert into types(type_id, type_name) values(?, ?)", type.getId(), type.getName());
    }

    public AccidentType getType(int id) {
        return jdbc.queryForObject("select * from types where type_id=?", new Object[]{id},
                (rs, row) -> new AccidentType(rs.getInt("type_id"), rs.getString("type_name")));
    }

    public Collection<AccidentType> getAllTypes() {
        return jdbc.query("select * from types",
                (rs, row) -> new AccidentType(rs.getInt("type_id"), rs.getString("type_name")));
    }

    public void addRule(Rule rule) {
        jdbc.update("insert into rules(rule_id, rule_name) values(?, ?)", rule.getId(), rule.getName());
    }

    public Rule getRule(int id) {
        return jdbc.queryForObject("select * from rules where rule_id=?", new Object[]{id},
        (rs, row) -> new Rule(rs.getInt("rule_id"), rs.getString("rule_name")));
    }

    public Collection<Rule> getAllRules() {
        return jdbc.query("select * from rules",
                (rs, row) -> new Rule(rs.getInt("rule_id"), rs.getString("rule_name")));
    }
}