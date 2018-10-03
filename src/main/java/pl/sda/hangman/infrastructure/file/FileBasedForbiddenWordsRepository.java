package pl.sda.hangman.infrastructure.file;

import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBasedForbiddenWordsRepository implements ForbiddenWordsRepository {
    private File file;
    private Scanner scanner;
    private List<String> forbiddenWords;

    public FileBasedForbiddenWordsRepository(File file) throws FileNotFoundException {
        this.scanner=new Scanner(file);
        initForbiddenWords();

    }
    private void initForbiddenWords() {
        this.forbiddenWords = new ArrayList<>();
        while (scanner.hasNext()) {
            String forbiddenWord = scanner.nextLine();
            forbiddenWords.add(forbiddenWord);
        }
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(forbiddenWords);
    }
}
