package pl.sda.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.library.application.ConsoleApplication;
import pl.sda.library.infrastructure.json.BooksDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryApplication {
    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<BooksDto> books = objectMapper.readValue(
//                new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\books.json"),
//                new TypeReference<List<BooksDto>>() {
//                });
//        System.out.println();
//
        new ConsoleApplication().start();
    }
}

//TODO mapowanie z jsona ktory jest lista