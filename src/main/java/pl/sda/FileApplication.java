package pl.sda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileApplication {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\test");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}
