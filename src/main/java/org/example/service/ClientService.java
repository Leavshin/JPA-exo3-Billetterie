package org.example.service;

import org.example.entity.Adresse;
import org.example.entity.Client;
import org.example.repository.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientService {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final ClientRepository clientRepository;

    public ClientService() {
        emf = Persistence.createEntityManagerFactory("billetterie");
        em = emf.createEntityManager();
        clientRepository = new ClientRepository(em);
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void addClient(Client client, Adresse adresse) {
        try {
            em.getTransaction().begin();
            clientRepository.addClient(client, adresse);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Gérer ou lancer à nouveau l'exception selon votre logique d'application
            throw new RuntimeException("Erreur lors de l'ajout du client", e);
        }
    }

    public void updateClient(Client client) {
        em.getTransaction().begin();
        try {
            clientRepository.updateClient(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Gérer l'exception
        }
    }

    public void deleteClient(long id) {
        em.getTransaction().begin();
        try {
            clientRepository.deleteClient(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Gérer l'exception
        }
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client getClientById(long id) {
        return clientRepository.getClientById(id);
    }
}
