package pl.sda.library.domain;

import org.apache.commons.lang3.StringUtils;
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

    public List<Book> findByTitle(String title) {
        if (StringUtils.isBlank(title)) {
            return Collections.emptyList();
        }
        Map<String, String> parameters = new HashMap<>();
        parameters.put("TITLE", title);
        return filterBook(parameters);
    }

    public List<Book> findByAuthor(String author) {
        if (StringUtils.isBlank(author)) {
            return Collections.emptyList();
        }
        Map<String, String> parameters = new HashMap<>();
        parameters.put("AUTHOR", author);
        return filterBook(parameters);
    }

    private List<Book> filterBook(Map<String, String> filterParameters) {
        return chain.filter(booksRepository.findAll(), filterParameters)
                .collect(Collectors.toList()); //TODO zapisywanie streama do listy

    }
}
