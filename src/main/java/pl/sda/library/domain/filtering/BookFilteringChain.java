package pl.sda.library.domain.filtering;

import pl.sda.library.domain.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BookFilteringChain {
    public BookFilteringChain() {
        this.chain = new ArrayList<>();
        this.chain.add(new BookAuthorFilterAction());
        this.chain.add(new BookTitleFilterAction());
    }

    private List<FilterAction> chain;

    public Stream<Book> filter(List<Book> books, Map<String, String> parameters) {
        Stream<Book> stream = books.stream();
        for (FilterAction action : chain) {
            if (action.isMyResponsibility((parameters))) {
                stream= action.action(stream, parameters);
            }
        }
        return stream;
    }

}
