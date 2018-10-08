package pl.sda.library.domain;

import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.ServerRequestInfo;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
import pl.sda.library.domain.filtering.BookFilteringChain;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BooksService {
    private BooksRepository booksRepository;
    private BookFilteringChain chain;

    public BooksService(BooksRepository booksRepository) {
        this.chain = new BookFilteringChain();
        this.booksRepository = booksRepository;
    }

    private List<Book> filterBook(Map<String, Object> filterParameters) {
        return chain.filter(booksRepository.findAll(), filterParameters)
                .collect(Collectors.toList()); //TODO zapisywanie streama do listy
    }

    public List<Book> findByTitle(String title) {
        return findBy("TITLE", title);
    }

    public List<Book> findByAuthor(String author) {
        return findBy("AUTHOR", author);
    }

    public List<Book> findByYear(Integer year) {
        if (year == null) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("YEAR", year);
        return filterBook(parameters);
    }

    public List<Book> findByLanguage(String language) {
        return findBy("LANGUAGE", language);
    }


    private List<Book> findBy(String key, String value) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(key, value);
        return filterBook(parameters);
    }

    public List<Book> findByPagesRange(Integer from, Integer to) throws InvalidPagesValueException {
        validatePagesRangeArgument(from, to);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("PAGES_FROM", from);
        parameters.put("PAGES_TO", to);
        return  filterBook(parameters);
    }

    private void validatePagesRangeArgument(Integer from, Integer to) throws InvalidPagesValueException{
        if ( from<0 || to< 0){
            throw new InvalidPagesValueException("Liczba stron nie moze byc ujemna");
        }
        if (from >to){
            throw new InvalidPagesValueException("Liczba minimalna stron nie moze byc wieksza niż total");
        }
    }

    public Map<String, Long> getAuthors() {
        List<Book> books = booksRepository.findAll();
        return books.stream()
                .map(e-> e.getAuthor())
                .distinct()//remove duplicates
                .collect(Collectors.toMap(
                        author->author,
                        author->books.stream()
                                .filter(book-> author.equals(book.getAuthor()))
                                .count()));
    }
}
