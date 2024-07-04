package org.example.service;

import org.example.entity.Billet;
import org.example.repository.BilletRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BilletService {
    private EntityManagerFactory emf;
    private EntityManager em;
    private BilletRepository billetRepository;

    public BilletService() {
        emf = Persistence.createEntityManagerFactory("billetterie");
        em = emf.createEntityManager();
        billetRepository = new BilletRepository(em);
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void addBillet(Billet billet) {
        billetRepository.addBillet(billet);
    }

    public void updateBillet(Billet billet) {
        billetRepository.updateBillet(billet.getId());
    }

    public void deleteBillet(int id) {
        billetRepository.deleteBillet(id);
    }

    public List<Billet> getAllBillets() {
        return billetRepository.getAllBillets();
    }

    public Billet getBilletById(int id) {
        return em.find(Billet.class, id);
    }

}
