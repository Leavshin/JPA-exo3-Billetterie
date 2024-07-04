package org.example.repository;

import org.example.entity.Client;
import org.example.entity.Event;
import org.example.entity.Billet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class BilletRepository {
    private final EntityManager em;

    public BilletRepository(EntityManager em) {
        this.em = em;
    }

    public void addBillet(Billet billet) {
        em.getTransaction().begin();

        Client client = billet.getClient();
        Event event = billet.getEvent();


        if (client != null && !em.contains(client)) {
            client = em.merge(client);
        }


        if (event != null && !em.contains(event)) {
            event = em.merge(event);
        }


        billet.setClient(client);
        billet.setEvent(event);


        em.persist(billet);

        em.getTransaction().commit();
    }


    public void updateBillet(int id) {
        em.getTransaction().begin();
        Billet billet = em.find(Billet.class, id);
        if (billet != null) {
            em.merge(billet);
        }
        em.getTransaction().commit();
    }

    public void deleteBillet(int id) {
        em.getTransaction().begin();
        Billet billet = em.find(Billet.class, id);
        if (billet != null) {
            em.remove(billet);
        }
        em.getTransaction().commit();
    }

    public List<Billet> getAllBillets() {
        TypedQuery<Billet> query = em.createQuery("SELECT t FROM Billet t", Billet.class);
        return query.getResultList();
    }


}