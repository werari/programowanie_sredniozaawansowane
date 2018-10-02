package pl.sda.hangman.application;

import pl.sda.hangman.application.ConsoleView;
import pl.sda.hangman.domain.GameFactory;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleView consoleView;
    private GameFactory gameFactory;

    public ConsoleApplication() {
        this.gameFactory = new GameFactory(new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Dupa", "Pan Tateusz")));
        this.consoleView = new ConsoleView(new Scanner(System.in));
    }

    public void start() {
        boolean flag =true;
        while(flag) {
            Integer menuOption = consoleView.mainMenu();
            switch (menuOption) {
                case 1:
                    startGame();
                    break;
                case 2:
                    System.out.println("Tutaj będa wyniki");
                    break;
                case 0:
                    System.out.println("Tutaj koniec");
                    break;
                default:
                    System.out.println("Wybrano zła opcje");
            }
        }
    }

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleView.displayGame(game);
            boolean result = game.addNextLetter(nextLetter);
            if (!result){
                consoleView.displayWrongLetterAdded();
            }
        } while (game.getStatus() == GameStatus.ACTIVE);
        if (game.getStatus()==GameStatus.WON){
            consoleView.displayGameWon();}
            else{
                consoleView.displayGameLost();

        }

    }
}

