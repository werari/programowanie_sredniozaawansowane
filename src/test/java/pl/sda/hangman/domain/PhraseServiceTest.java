package pl.sda.hangman.domain;

import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistExcpetion;
import pl.sda.hangman.domain.port.PhraseRepository;

public class PhraseServiceTest {
    @Test
    public void addPhraseShouldAddNewPhrase() throws Exception{
        //give
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.any())).thenReturn(false);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository,forbiddenWordsValidator );
        //when
        phraseService.addPhrase("phrase with forbiddenWord");

    }
    @Test (expected = PhraseAlreadyExistExcpetion.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseAlreadyExist() throws Exception{
        //give
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.any())).thenReturn(true);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository,forbiddenWordsValidator );
        //when
        phraseService.addPhrase("phrase with forbiddenWord");

    }
    @Test(expected= ForbiddenWordsInPhraseException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseContainsForbiddenWords() throws Exception{
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(false);
        Mockito.when(phraseRepository.contains(Mockito.any())).thenReturn(false);
        PhraseService phraseService = new PhraseService(phraseRepository,forbiddenWordsValidator );
        //when
        phraseService.addPhrase("phrase with forbiddenWord");


    }
}
