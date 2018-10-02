package pl.sda.hangman.application;

import pl.sda.hangman.domain.model.Game;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }
    public Integer mainMenu(){
        System.out.println("1. Start");
        System.out.println("2. Wyniki");
        System.out.println("0. Koniec");
        return getIntValue();
    }
    private Integer getIntValue(){
        int value= scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public char displayGame(Game game) {
        char[] phraseStatus = game.getPhraseStatus();
        int leftAttempts= game.getLeftAttempts();
        String phraseStatusAsString = new String(phraseStatus);
        System.out.println(phraseStatusAsString + " ("+ leftAttempts+ ")");
        return scanner.nextLine().charAt(0);
    }
}
