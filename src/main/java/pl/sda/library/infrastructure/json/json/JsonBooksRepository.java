package pl.sda.library.infrastructure.json.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;
import pl.sda.library.infrastructure.json.json.BooksDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonBooksRepository implements BooksRepository {

    private File fileWihtJson;
    private List<BooksDto> books;

    public JsonBooksRepository(File fileWithJson) {
        this.fileWihtJson = fileWithJson;
    }

    @Override
    public List<Book> findAll() {
        if (books == null) {
            instantiateBooks();
        }
        List<Book> booksToReturn= new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            BooksDto booksDto = books.get(i);
            booksToReturn.add(booksDto.mapToDomain(i));
        }
        return booksToReturn;
    }

    private void instantiateBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.books = objectMapper.readValue(fileWihtJson, new TypeReference<List<BooksDto>>() {
            });
        } catch (IOException e) {
            System.out.println("Error: Blad przy czytaniu jsona");
            this.books = Collections.emptyList();

        }
    }
}

