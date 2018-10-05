package pl.sda.library.application;

import pl.sda.library.domain.BooksService;
import pl.sda.library.domain.port.BooksRepository;
import pl.sda.library.infrastructure.json.JsonBooksRepository;

import java.io.File;
import java.util.Scanner;

public class ConsoleApplication {
    private ConsoleViews consoleViews;
    private BooksService booksService;

    public ConsoleApplication() {
        BooksRepository booksRepository= new JsonBooksRepository(new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\books.json"));
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.booksService= new BooksService(booksRepository);
    }

    public void start() {
        Integer option = consoleViews.menu();
        switch (option) {
            case 1:
                showBooks();
                break;
            case 2:
                break;
            case 0:
                System.out.println("Koniec");
                break;
            default:
                System.out.println("Bledna opcja");
        }
    }

    private void showBooks() {
        Integer option =consoleViews.showBooksMenu();
        switch (option){
            case 1:
                String title = consoleViews.getBookName();


        }
    }
}
