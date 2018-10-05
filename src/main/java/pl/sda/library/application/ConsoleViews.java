package pl.sda.library.application;

import pl.sda.library.domain.model.Book;

import java.util.List;
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
        System.out.println(book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    public String getBookAuthor() {
        System.out.println("Podaj autora");
       return scanner.nextLine();
    }
}
