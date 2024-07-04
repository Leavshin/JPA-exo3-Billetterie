package org.example.repository;

import org.example.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventRepository {
    private final EntityManager em;

    public EventRepository(EntityManager em) {
        this.em = em;
    }

    public void addEvent(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }

    public void updateEvent(Event event) {
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();
    }

    public void deleteEvent(int id) {
        em.getTransaction().begin();
        Event event = em.find(Event.class, id);
        if (event != null) {
            em.remove(event);
        }
        em.getTransaction().commit();
    }

    public List<Event> getAllEvent() {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }
}