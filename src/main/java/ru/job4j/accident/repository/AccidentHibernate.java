package ru.job4j.accident.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private static final Logger LOG = LogManager.getLogger(AccidentHibernate.class.getName());
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        try (session) {
            final Transaction tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Transactional
    public void add(Accident accident) {
        this.tx(session -> {
            session.saveOrUpdate(accident);
            return true;
        });
    }

    public Accident get(int id) {
        return this.tx(session -> session.createQuery(
                "select ac from Accident ac join fetch ac.type "
                        + "join fetch ac.rules where ac.id = :acId", Accident.class)
                .setParameter("acId", id).getSingleResult());
    }

    public List<Accident> getAll() {
        return this.tx(session -> session.createQuery(
                "select distinct ac from Accident ac join fetch ac.type "
                        + "join fetch ac.rules", Accident.class).getResultList());
    }

    public AccidentType getType(int id) {
        return this.tx(session -> session.createQuery(
                "select act from AccidentType act where act.id = :actId", AccidentType.class)
                .setParameter("actId", id).getSingleResult());
    }

    public Collection<AccidentType> getAllTypes() {
        return this.tx(session -> session.createQuery(
                "select ac_t from AccidentType ac_t", AccidentType.class).getResultList());
    }

    public Rule getRule(int id) {
        return this.tx(session -> session.createQuery(
                "select r from Rule r where r.id = :rId", Rule.class)
                .setParameter("rId", id).getSingleResult());
    }

    public Collection<Rule> getAllRules() {
        return this.tx(session -> session.createQuery(
                "select r from Rule r", Rule.class).getResultList());
    }
}