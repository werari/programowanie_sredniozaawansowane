package pl.sda.hangman.domain.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void addNextLetterShouldReturnTrueAndAddCharacterToPhraseStatusWhenLetterCanBeAdded() {
         //given
        Game game = createSampleGame();
        //when
        boolean result= game.addNextLetter('a');
        //then
        Assert.assertTrue(result);
        Assert.assertArrayEquals(new char[] {'A', '_', 'a', ' ', '_', 'a', ' ', '_', '_', '_', 'a'}, game.getPhraseStatus());

    }

    @Test
    public void addNextLetterShouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsNotPresentInPhrase(){

        //given
        Game game = createSampleGame();
        //when
        boolean resultFalse= game.addNextLetter('r');
        //then
        Assert.assertFalse(resultFalse);


    }
    @Test
    public void addNextLetterShouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsAddedSecondTime(){

    }
    @Test
    public void addNextLetterShouldChangeStatusToWonWhenLastLetterIsAdded(){

    }
    @Test
    public void addNextLetterShouldChangeStatusToLoseWhenLeftAttemptsEqualsZero(){

    }
    private Game createSampleGame() {
        return Game.builder()
                .phrase("ala ma kota")
                .phraseStatus(new char[]{'_', '_', '_', ' ', '_', '_', ' ', '_', '_', '_', '_'})
                .leftAttempts(5)
                .startData(Instant.now())
                .status(GameStatus.ACTIVE)
                .build();
    }
}