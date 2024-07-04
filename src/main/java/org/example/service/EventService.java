package org.example.service;

import org.example.entity.Event;
import org.example.repository.EventRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EventService {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EventRepository eventRepository;

    public EventService() {
        emf = Persistence.createEntityManagerFactory("billetterie");
        em = emf.createEntityManager();
        eventRepository = new EventRepository(em);
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void addEvent(Event event) {
        eventRepository.addEvent(event);
    }

    public void updateEvent(Event event) {
        eventRepository.updateEvent(event);
    }

    public void deleteEvent(int id) {
        eventRepository.deleteEvent(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvent();
    }

    public Event getEventById(int eventId) {
        return em.find(Event.class, eventId);
    }
}
