package org.example.controller;

import org.example.entity.Event;
import org.example.service.EventService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class IHMEvent {
    private final EventService eventService;
    private final Scanner scanner;

    public IHMEvent(Scanner scanner) {
        this.eventService = new EventService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Menu Event");
            System.out.println("1. Créer un event");
            System.out.println("2. Liste de tous les events");
            System.out.println("3. Mettre à jour un event");
            System.out.println("4. Supprimer un event");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createEvent();
                case 2 -> listEvents();
                case 3 -> updateEvent();
                case 4 -> deleteEvent();
                default -> System.out.println("Choix invalide");
            }
        }
    }

    public void createEvent() {
        System.out.println("Créer un nouvel event :");
        System.out.print("Entrer le nom de l'event : ");
        String nom = scanner.nextLine();
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Entrer la date de l'event (dd-MM-yyyy): ");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(scanner.nextLine(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez réessayer.");
            }
        }
        LocalTime heure = null;
        while (heure == null) {
            try {
                System.out.print("Entrer l'heure de l'event (HH:mm): ");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                heure = LocalTime.parse(scanner.nextLine(), timeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Heure invalide. Veuillez réessayer.");
            }
        }
        int capacite = -1;
        while (capacite < 0) {
            try {
                System.out.print("Entrer la capacité de l'event : ");
                capacite = Integer.parseInt(scanner.nextLine());
                if (capacite < 0) {
                    System.out.println("La capacité doit être un nombre positif.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Capacité invalide. Veuillez entrer un nombre.");
            }
        }
        Event event = Event.builder()
                .nom(nom)
                .date(date)
                .heure(heure)
                .capacite(capacite)
                .build();
        eventService.addEvent(event);
        System.out.println("Event créé");
    }

    public void listEvents() {
        List<Event> events = eventService.getAllEvents();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void updateEvent() {
        System.out.print("Entrer l'ID de l'event à mettre à jour : ");
        int id = Integer.parseInt(scanner.nextLine());
        Event event = eventService.getAllEvents()
                .stream().filter(e -> e.getId() == id)
                .findFirst().orElse(null);

        if (event != null) {
            System.out.print("Entrer le nouveau nom (ou cliquer sur Entrer) : "
                    + event.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                event.setNom(nom);
            }

            System.out.print("Entrer la nouvelle date (YYYY-MM-DD) ou cliquer sur Entrer : "
                    + event.getDate() + "): ");
            String date = scanner.nextLine();
            if (!date.isEmpty()) {
                event.setDate(LocalDate.parse(date));
            }

            System.out.print("Entrer la nouvelle heure (HH:MM) ou cliquer sur Entrer : "
                    + event.getHeure() + "): ");
            String heure = scanner.nextLine();
            if (!heure.isEmpty()) {
                event.setHeure(LocalTime.parse(heure));
            }

            System.out.print("Entrer la nouvelle capacité (ou cliquer sur Entrer) : "
                    + event.getCapacite() + "): ");
            String capacite = scanner.nextLine();
            if (!capacite.isEmpty()) {
                event.setCapacite(Integer.parseInt(capacite));
            }

            eventService.updateEvent(event);
            System.out.println("Event mis à jour");
        } else {
            System.out.println("Event introuvable");
        }
    }

    public void deleteEvent() {
        System.out.print("Entrer l'ID de l'event à supprimer ");
        int id = Integer.parseInt(scanner.nextLine());

        eventService.deleteEvent(id);
        System.out.println("Event supprimé");
    }
}