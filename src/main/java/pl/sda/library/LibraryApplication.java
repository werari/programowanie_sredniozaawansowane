package pl.sda.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryApplication {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Books> books = objectMapper.readValue(
                new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\books.json"),
                new TypeReference<List<Books>>() {
                });
        System.out.println();
    }
}

//TODO mapowanie z jsona ktory jest lista