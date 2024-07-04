package org.example.controller;

import java.util.Scanner;

public class IHMGlobal {

    private Scanner scanner;
    private IHMClient ihmClient;
    private IHMBillet ihmBillet;
    private IHMEvent ihmEvent;

    public IHMGlobal() {
        this.scanner = new Scanner(System.in);
        ihmBillet = new IHMBillet(scanner);
        ihmClient = new IHMClient(scanner);
        ihmEvent = new IHMEvent(scanner);
    }

    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Billetterie -----");
            System.out.println("1/ Menu client");
            System.out.println("2/ Menu Billet");
            System.out.println("3/ Menu Event");
            entry = scanner.nextLine();
            switch(entry){
                case "1"-> ihmClient.start();
                case "2"-> ihmBillet.start();
                case "3"-> ihmEvent.start();
                default -> {
                    return;
                }
            }
        }
    }
}
