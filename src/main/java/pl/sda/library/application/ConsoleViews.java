package pl.sda.library.application;

import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Biblioteka");
        System.out.println("1.Ksiazki");
        System.out.println("2. ...");
        System.out.println("0. Koniec");
        return getNumberFromUser();
    }

    private Integer getNumberFromUser() {
        Integer option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String getBookName() {
        System.out.println("Podaj tytul ksizki");
        return scanner.nextLine();
    }

    public Integer showBooksMenu() {
        System.out.println("1. Znajdz po nazwie");
        System.out.println("2. Znajdz po autorze");
        System.out.println("0. Wyjdz");
        return getNumberFromUser();
    }
}
