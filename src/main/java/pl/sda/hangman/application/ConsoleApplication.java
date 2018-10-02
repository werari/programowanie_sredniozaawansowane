package pl.sda.hangman.application;

import pl.sda.hangman.application.ConsoleView;
import pl.sda.hangman.domain.GameFactory;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;

public class ConsoleApplication {

    private ConsoleView consoleView;
    private GameFactory gameFactory;

    public ConsoleApplication(ConsoleView consoleView) {

        this.consoleView = consoleView;
        this.gameFactory = gameFactory;
    }

    public void start() {
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

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleView.displayGame(game);
        } while (game.getStatus() == GameStatus.ACTIVE);
    }
}

