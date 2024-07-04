package org.example.controller;

import org.example.entity.Adresse;
import org.example.entity.Client;
import org.example.service.ClientService;

import java.util.List;
import java.util.Scanner;

public class IHMClient {
    private final ClientService clientService;
    private final Scanner scanner;

    public IHMClient(Scanner scanner) {
        this.clientService = new ClientService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Menu Client");
            System.out.println("1. Créer un client");
            System.out.println("2. Liste de tous les clients");
            System.out.println("3. Mettre à jour un client");
            System.out.println("4. Supprimer un client");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createClient();
                case 2 -> listClients();
                case 3 -> updateClient();
                case 4 -> deleteClient();
                default -> System.out.println("Choix invalide");
            }
        }
    }

    public void createClient() {
        System.out.println("Créer un nouveau client :");
        System.out.print("Entrer le nom du client : ");
        String nom = scanner.nextLine();
        System.out.print("Entrer le prénom du client : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrer l'âge du client : ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrer le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();
        System.out.println("Adresse :");
        System.out.print("Numéro et rue : ");
        String rue = scanner.nextLine();
        System.out.print("Ville : ");
        String ville = scanner.nextLine();

        Adresse adresse = Adresse.builder()
                .rue(rue)
                .ville(ville)
                .build();

        Client client = Client.builder()
                .nom(nom)
                .prenom(prenom)
                .age(age)
                .telephone(telephone)
                .adresse(adresse)
                .build();

        clientService.addClient(client, adresse);

        System.out.println("Client créé");
    }

    public void listClients() {
        List<Client> clients = clientService.getAllClients();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public void updateClient() {
        System.out.print("Entrer l'ID du client à mettre à jour : ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = clientService.getAllClients().stream()
                .filter(c -> c.getId() == id).findFirst().orElse(null);

        if (client != null) {
            System.out.print("Entrer le nouveau nom (ou appuyer sur Entrer) : "
                    + client.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                client.setNom(nom);
            }

            System.out.print("Enter le nouveau prénom new first name (ou appuyer sur Entrer) : "
                    + client.getPrenom() + "): ");
            String prenom = scanner.nextLine();
            if (!prenom.isEmpty()) {
                client.setPrenom(prenom);
            }

            System.out.print("Entrer le nouvel âge new age (ou appuyer sur Entrer) : "
                    + client.getAge() + "): ");
            String age = scanner.nextLine();
            if (!age.isEmpty()) {
                client.setAge(Integer.parseInt(age));
            }

            System.out.print("Entrer le nouveau numéro de téléphone (ou appuyer sur Entrer) : "
                    + client.getTelephone() + "): ");
            String telephone = scanner.nextLine();
            if (!telephone.isEmpty()) {
                client.setTelephone(telephone);
            }

            clientService.updateClient(client);
            System.out.println("Client mis à jour");
        } else {
            System.out.println("Client introuvable");
        }
    }

    public void deleteClient() {
        System.out.print("Entrer l'ID du client à supprimer ");
        int id = Integer.parseInt(scanner.nextLine());

        clientService.deleteClient(id);
        System.out.println("Client supprimé");
    }
}