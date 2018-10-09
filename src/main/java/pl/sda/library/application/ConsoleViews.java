package pl.sda.library.application;

import pl.sda.library.domain.model.Book;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Biblioteka");
        System.out.println("1.Ksiazki");
        System.out.println("2. Pokaz autorow");
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
        System.out.println("3. Znajdz po dacie wydania");
        System.out.println("4. Znajdz po języku wydania");
        System.out.println("5. Znajdzie po zakresie stron");
        System.out.println("0. Wyjdz");
        return getNumberFromUser();
    }

    public void waitForAction() {
        System.out.println("Wcisnij enter zeby kontynuowac");
        scanner.nextLine();
    }

    public void displayBooks(List<Book> books) {
        if (books.size() > 0) {
//            books.forEach(this::displayShortenedBook);
            //tożsame z poniższym, ale tylko dla prostych lambdach, przy wielu argumentach to nie zadziała
            books.forEach(book -> displayShortenedBook(book));
        } else {
            System.out.println("Brak książek do wyświetlania");
        }
        waitForAction();
    }

    private void displayShortenedBook(Book book) {
        System.out.println("==========================");
        System.out.println(book.getId()+ ". "+book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    public String getBookAuthor() {
        System.out.println("Podaj autora");
       return scanner.nextLine();
    }

    public Integer getYear() {
        System.out.println("Podaj rok wydania");
        return scanner.nextInt();
    }

    public String getLanguage() {
        System.out.println("Podaj język");
        return scanner.nextLine();
    }


    public Integer getFromPages() {
        System.out.println("Podaj dolny zakres stron");
        return getNumberFromUser();
    }

    public Integer getToPages() {
        System.out.println("Podaj górny zakres stron");
        return getNumberFromUser();
    }

    public void displayError(String message) {
        System.out.println("ERROR"+ message);
        waitForAction();
    }

    public void displayAuthors(Map<String, Long> authors) {
        authors.entrySet()
                .forEach(e-> System.out.println(e.getKey() + " - "+ e.getValue()));
    }

    public String getBookId() {
        System.out.println("Podaj id ksiązki");
        return scanner.nextLine();
    }

    public String getUserName() {
        System.out.println("Podaje nazwe Uzytkownika");
        return scanner.nextLine();
    }

    public void borrowSuccess(String title) {
        System.out.println("Udało się wypozyczyć "+ title);
    }

    public void borrowFailure() {
        System.out.println("Nie udało sie wypozyczyć książki");
    }
}
