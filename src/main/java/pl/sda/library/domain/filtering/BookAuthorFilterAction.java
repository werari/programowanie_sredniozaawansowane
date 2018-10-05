package pl.sda.library.domain.filtering;

import org.apache.commons.lang3.StringUtils;
import pl.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public class BookAuthorFilterAction implements FilterAction {

    @Override
    public boolean isMyResponsibility(Map<String, Object> parameters) {
        return parameters.containsKey("AUTHOR");
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
        return stream.filter(e-> StringUtils.containsIgnoreCase(e.getAuthor(), (String) parameters.get("AUTHOR")));
    }


}