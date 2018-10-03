package pl.sda.hangman.application;

import pl.sda.hangman.domain.ForbiddenWordsValidator;
import pl.sda.hangman.domain.GameFactory;
import pl.sda.hangman.domain.PhraseService;
import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistExcpetion;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.infrastructure.file.FileBasedForbiddenWordsRepository;
import pl.sda.hangman.infrastructure.memory.InMemoryForbiddenWordsRepository;
import pl.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleView consoleView;
    private GameFactory gameFactory;
    private PhraseService phraseService;

    public ConsoleApplication() throws FileNotFoundException {
        InMemoryPhraseRepository phraseRepository = new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Kot ma ale", "Pan Tateusz"));
        ForbiddenWordsValidator forbiddenWordsValidator = new ForbiddenWordsValidator(
                new FileBasedForbiddenWordsRepository(new File("C:\\Users\\W\\IdeaProjects\\programowanie_sredniozaawansowane\\src\\main\\resources\\hangman\\forbiddenWords.txt")));
        this.gameFactory = new GameFactory(phraseRepository);
        this.consoleView = new ConsoleView(new Scanner(System.in));
        this.phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            Integer menuOption = consoleView.mainMenu();
            switch (menuOption) {
                case 1:
                    startGame();
                    break;
                case 2:
                    addPhrase();
                    break;
                case 0:
                    System.out.println("Tutaj koniec");
                    break;
                default:
                    System.out.println("Wybrano zła opcje");
            }
        }
    }

    private void addPhrase() {
        String phrase = consoleView.addPhraseMessage();
        try {
            phraseService.addPhrase(phrase);
            consoleView.displayPhraseAddedSuccessfully(phrase);
        } catch (ForbiddenWordsInPhraseException e) {
            consoleView.displayPhraseContainsForbiddenWords();
        } catch (PhraseAlreadyExistExcpetion phraseAlreadyExistExcpetion) {
            consoleView.displayPhraseAlreadyExist();
        }
    }

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleView.displayGame(game);
            boolean result = game.addNextLetter(nextLetter);
            if (!result) {
                consoleView.displayWrongLetterAdded();
            }
        } while (game.getStatus() == GameStatus.ACTIVE);
        if (game.getStatus() == GameStatus.WON) {
            consoleView.displayGameWon();
        } else {
            consoleView.displayGameLost();

        }

    }
}

