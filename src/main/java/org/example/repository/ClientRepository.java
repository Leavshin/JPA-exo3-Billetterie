package org.example.repository;

import org.example.entity.Adresse;
import org.example.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientRepository {
    private final EntityManager em;

    public ClientRepository(EntityManager em) {
        this.em = em;
    }

    public void addClient(Client client, Adresse adresse) {
        em.persist(client);
    }

    public void updateClient(Client client) {
        em.persist(client);
    }

    public void deleteClient(long id) {
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        if (client != null) {
            em.remove(client);
        }
        em.getTransaction().commit();
    }

    public List<Client> getAllClients() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    public Client getClientById(long id) {
        return em.find(Client.class, id);
    }
}
