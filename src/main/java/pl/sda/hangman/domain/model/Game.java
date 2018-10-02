package pl.sda.hangman.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
@Builder
public class Game {
    private String phrase;
    private char[] phraseStatus;
    private Instant startData;
    private GameStatus status;
    private Integer leftAttempts;

    public boolean addNextLetter (char letter){
        return true;
    }

}
