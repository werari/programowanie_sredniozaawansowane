package pl.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenWordsValidatorTest {

    private ForbiddenWordsRepository forbiddenWordsRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    @Before
    public void before() {
        forbiddenWordsRepository = Mockito.mock(ForbiddenWordsRepository.class);
        forbiddenWordsValidator = new ForbiddenWordsValidator(forbiddenWordsRepository);
    }

    private void mockRepository(List<String> forbiddenWords){
        Mockito.when(forbiddenWordsRepository.findAll()).thenReturn(forbiddenWords);
    }
    @Test
    public void validateShouldReturnTrueWhenPhraseDoesNotContainsForbiddenWords() {
        //given
        mockRepository(Arrays.asList("forbidden"));

        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase");

        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWords() {
        //given
        mockRepository(Arrays.asList("forbidden"));

        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with forbidden word");

        //then
        Assert.assertFalse(isValid);

    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsWithWhitespace() {
        //given

        mockRepository(Arrays.asList("forbidden"));

        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with f o r b i d d e n word");

        //then
        Assert.assertFalse(isValid);

    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsUppercaseForbiddenWordsWithWhitespace() {
        //given

        mockRepository(Arrays.asList("forbidden"));

        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with F O R B I D D E N word");

        //then
        Assert.assertFalse(isValid);

    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsCombineToForbiddenPhrase() {
        //given

        mockRepository(Arrays.asList("forbidden"));

        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with F O R B I D D E N word");

        //then
        Assert.assertFalse(isValid);

    }
}