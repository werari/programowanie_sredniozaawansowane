package pl.sda.hangman.domain.exceptions;

public class PhraseAlreadyExistExcpetion extends GameException {
    public PhraseAlreadyExistExcpetion(String message) {
        super(message);
    }
}
