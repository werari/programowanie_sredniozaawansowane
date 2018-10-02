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
        boolean result = game.addNextLetter('a');
        //then
        Assert.assertTrue(result);
        Assert.assertArrayEquals(new char[]{'A', '_', 'a', ' ', '_', 'a', ' ', '_', '_', '_', 'a'}, game.getPhraseStatus());

    }

    @Test
    public void addNextLetterShouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsNotPresentInPhrase() {

        //given
        Game game = createSampleGame();
        Integer leftAttempts = game.getLeftAttempts();
        //when
        boolean resultFalse = game.addNextLetter('r');
        //then
        Assert.assertFalse(resultFalse);
        Assert.assertArrayEquals(new char[]{'_', '_', '_', ' ', '_', '_', ' ', '_', '_', '_', '_'}, game.getPhraseStatus());
        Assert.assertEquals(((Integer) (leftAttempts - 1)), game.getLeftAttempts());

    }

    @Test
    public void addNextLetterShouldReturnFalseAndDoNotUpdatePhraseStatusAndDecrementLeftAttemptsWhenLetterIsAddedSecondTime() {
        //given
        Game game = createSampleGame();
        Integer leftAttempts = game.getLeftAttempts();
        //when
        game.addNextLetter('a');
        boolean result = game.addNextLetter('a');
        //then
        Assert.assertFalse(result);
        Assert.assertArrayEquals(new char[]{'A', '_', 'a', ' ', '_', 'a', ' ', '_', '_', '_', 'a'}, game.getPhraseStatus());
        Assert.assertEquals(((Integer) (leftAttempts - 1)), game.getLeftAttempts());
    }

    @Test
    public void addNextLetterShouldChangeStatusToWonWhenLastLetterIsAdded() {
        //given
        Game game = createSampleGameWithCustomPraseState(new char[]{'A', '_', 'a', ' ', 'm', 'a', ' ', 'k', 'o', 't', 'a'});

        //when
        boolean result = game.addNextLetter('l');
        //then
        Assert.assertTrue(result);
        Assert.assertArrayEquals(new char[]{'A', 'l', 'a', ' ', 'm', 'a', ' ', 'k', 'o', 't', 'a'}, game.getPhraseStatus());
        Assert.assertEquals(GameStatus.WON, game.getStatus());
    }

    @Test
    public void addNextLetterShouldChangeStatusToLoseWhenLeftAttemptsEqualsZero() {
        //given
        Game game = createSampleGameBuilder()
                .leftAttempts(1)
                .build();
        //when
        boolean result = game.addNextLetter('z');
        //then
        Assert.assertFalse(result);
        Assert.assertEquals((Integer) 0, game.getLeftAttempts());
        Assert.assertEquals(GameStatus.LOST, game.getStatus());
    }

    private Game createSampleGameWithCustomPraseState(char[] phraseState) {
        return createSampleGameBuilder()
                .phraseStatus(phraseState)
                .build();
    }

    private Game createSampleGame() {
        return createSampleGameBuilder()
                .build();
    }

    private Game.GameBuilder createSampleGameBuilder() {
        return Game.builder()
                .phrase("Ala ma kota")
                .phraseStatus(new char[]{'_', '_', '_', ' ', '_', '_', ' ', '_', '_', '_', '_'})
                .leftAttempts(5)
                .startData(Instant.now())
                .status(GameStatus.ACTIVE);
    }
}