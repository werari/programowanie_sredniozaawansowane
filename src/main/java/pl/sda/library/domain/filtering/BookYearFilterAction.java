package pl.sda.library.domain.filtering;


import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BookYearFilterAction extends SimpleAbstractFilterAction {
//    @Override
//    public boolean isMyResponsibility(Map<String, Object> parameters) {
//        return parameters.containsKey("YEAR");
//    }
//
//    @Override
//    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
//        return stream.filter(e-> e.getYear().equals(parameters.get("YEAR")));
//    }

    @Override
    protected String getKey() {
        return "YEAR";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e-> e.getYear().equals(parameters.get("YEAR"));
    }
}
