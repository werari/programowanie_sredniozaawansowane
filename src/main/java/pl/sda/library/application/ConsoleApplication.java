package pl.sda.library.application;

import org.assertj.core.internal.bytebuddy.description.field.FieldDescription;
import pl.sda.library.domain.BooksService;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;
import pl.sda.library.infrastructure.json.JsonBooksRepository;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private ConsoleViews consoleViews;
    private BooksService booksService;

    public ConsoleApplication() {
        BooksRepository booksRepository = new JsonBooksRepository(new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\books.json"));
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.booksService = new BooksService(booksRepository);
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            Integer option = consoleViews.menu();
            switch (option) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Bledna opcja");
            }
        }
    }

    private void showBooks() {
        Integer option = consoleViews.showBooksMenu();
        switch (option) {
            case 1:
                String title = consoleViews.getBookName();
                // long before = System.currentTimeMillis();
                List<Book> books = booksService.findByTitle(title);
                // long after = System.currentTimeMillis();
                // System.out.println(after-before);
                consoleViews.displayBooks(books);
                break;
//TODO zakomentowane to metoda na mierzenie czasu ile zajeło wykonanie jej;
            case 2:
                String author = consoleViews.getBookAuthor();
                List<Book> booksAuthor = booksService.findByAuthor(author);
                consoleViews.displayBooks(booksAuthor);
                break;
            case 3:
                Integer year = consoleViews.getYear();
                List<Book> bookYear = booksService.findByYear(year);
                consoleViews.displayBooks(bookYear);
                break;
            case 4:
                //filter by language
                String language= consoleViews.getLanguage();
                List<Book> bookLanguage= booksService.findByLanguage(language);
                consoleViews.displayBooks(bookLanguage);
                break;
            case 5:
                //filter by pages- with ranges
                findByPagesRange();
                break;
            default:
                System.out.println("Błąd");
        }
    }

    private void findByPagesRange() {
        try {
            Integer from = consoleViews.getFromPages();
            Integer to = consoleViews.getToPages();
            List<Book> bookPages = booksService.findByPagesRange(from, to);
            consoleViews.displayBooks(bookPages);
        } catch (InvalidPagesValueException e){
            consoleViews.displayError("Błedne dane");

        }
    }
}
