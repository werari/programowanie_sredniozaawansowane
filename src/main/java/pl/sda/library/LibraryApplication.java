package pl.sda.library;

import pl.sda.library.application.ConsoleApplication;

import java.io.IOException;

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