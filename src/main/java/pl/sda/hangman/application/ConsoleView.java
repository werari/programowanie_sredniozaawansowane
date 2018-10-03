package pl.sda.hangman.application;

import pl.sda.hangman.domain.model.Game;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer mainMenu() {
        System.out.println("1. Start");
        System.out.println("2. Dodaj fraze");
        System.out.println("0. Koniec");
        return getIntValue();
    }

    private Integer getIntValue() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public char displayGame(Game game) {
        char[] phraseStatus = game.getPhraseStatus();
        int leftAttempts = game.getLeftAttempts();
        String phraseStatusAsString = new String(phraseStatus);
        System.out.println(phraseStatusAsString + " (" + leftAttempts + ")");
        return scanner.nextLine().charAt(0);
    }

    public void displayWrongLetterAdded() {
        System.out.println("Podano niepoprawna litere");
    }

    public void displayGameWon() {
        System.out.println("Wygrana");
        waitForAction();
    }

    private void waitForAction() {
        System.out.println("Wcisnij enter, zbey kontynuowac");
        scanner.nextLine();
    }

    public void displayGameLost() {
        System.out.println("Przegana");
        waitForAction();
    }

    public String addPhraseMessage() {
        System.out.println("Podaj frazę");
        return scanner.nextLine();
    }

    public void displayPhraseContainsForbiddenWords() {
        System.out.println("Podana fraza zawiera zabraonione słowa");
        waitForAction();
    }

    public void displayPhraseAlreadyExist() {
        System.out.println("Podana fraza już istnieje");
        waitForAction();
    }

    public void displayPhraseAddedSuccessfully(String phrase) {
        System.out.println("Dodano nowa fraze");
        waitForAction();
    }
}
