package org.example.controller;

import org.example.entity.Billet;
import org.example.entity.Client;
import org.example.entity.Event;
import org.example.service.BilletService;
import org.example.service.ClientService;
import org.example.service.EventService;
import org.example.util.TypePlaces;

import java.util.List;
import java.util.Scanner;

public class IHMBillet {
    private final BilletService billetService;
    private final Scanner scanner;

    public IHMBillet(Scanner scanner) {
        this.billetService = new BilletService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Menu Billet");
            System.out.println("1. Créer un billet");
            System.out.println("2. Liste de tous les billets");
            System.out.println("3. Mettre à jour un billet");
            System.out.println("4. Supprimer un billet");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createBillet();
                case 2 -> listBillets();
                case 3 -> updateBillet();
                case 4 -> deleteBillet();
                default -> System.out.println("Choix invalide");
            }
        }
    }

    public void createBillet() {
        System.out.println("Créer un nouveau billet :");
        System.out.print("Entrer le numéro du billet : ");
        int numeroBillet = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrer le type de billet (STANDARD, GOLD, VIP): ");
        TypePlaces typePlaces = TypePlaces.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Entrer l'ID du client : ");
        int clientId = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrer l'ID de l'événement : ");
        int eventId = Integer.parseInt(scanner.nextLine());

        ClientService clientService = new ClientService();
        Client client = clientService.getClientById(clientId);
        EventService eventService = new EventService();
        Event event = eventService.getEventById(eventId);

        if (client != null && event != null) {
            Billet billet = Billet.builder()
                    .numeroBillet(numeroBillet)
                    .typePlaces(typePlaces)
                    .client(client)
                    .event(event)
                    .build();

            billetService.addBillet(billet);
            System.out.println("Billet créé");
        } else {
            System.out.println("Client ou événement introuvable");
        }
    }

    public void listBillets() {
        List<Billet> billets = billetService.getAllBillets();
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }

    public void updateBillet() {
        System.out.print("Entrer l'ID du billet à mettre à jour : ");
        int id = Integer.parseInt(scanner.nextLine());
        Billet billet = billetService.getBilletById(id);
        if (billet != null) {
            System.out.print("Entrer le nouveau numéro de billet (ou appuyer sur Entrée pour garder l'actuel): ");
            String ticketNumber = scanner.nextLine();
            if (!ticketNumber.isEmpty()) {
                billet.setNumeroBillet(Integer.parseInt(ticketNumber));
            }

            System.out.print("Entrer le nouveau type de billet (STANDARD, GOLD, VIP) ou appuyer sur Entrée pour garder l'actuel: ");
            String typePlaces = scanner.nextLine();
            if (!typePlaces.isEmpty()) {
                billet.setTypePlaces(TypePlaces.valueOf(typePlaces.toUpperCase()));
            }

            System.out.print("Entrer le nouvel ID du client (ou appuyer sur Entrée pour garder l'actuel): ");
            String clientId = scanner.nextLine();
            if (!clientId.isEmpty()) {
                ClientService clientService = new ClientService();
                Client client = clientService.getClientById(Integer.parseInt(clientId));
                if (client != null) {
                    billet.setClient(client);
                } else {
                    System.out.println("Client introuvable");
                }
            }

            System.out.print("Entrer le nouvel ID de l'événement (ou appuyer sur Entrée pour garder l'actuel): ");
            String eventId = scanner.nextLine();
            if (!eventId.isEmpty()) {
                EventService eventService = new EventService();
                Event event = eventService.getEventById(Integer.parseInt(eventId));
                if (event != null) {
                    billet.setEvent(event);
                } else {
                    System.out.println("Événement introuvable");
                }
            }

            billetService.updateBillet(billet);
            System.out.println("Billet mis à jour");
        } else {
            System.out.println("Billet introuvable");
        }
    }

    public void deleteBillet() {
        System.out.print("Entrer l'ID du billet à supprimer : ");
        int id = Integer.parseInt(scanner.nextLine());

        billetService.deleteBillet(id);
        System.out.println("Billet supprimé");
    }
}
