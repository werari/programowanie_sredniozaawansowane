package pl.sda.hangman.domain;

import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistExcpetion;
import pl.sda.hangman.domain.port.PhraseRepository;

public class PhraseService {

    private PhraseRepository phraseRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    public PhraseService(PhraseRepository phraseRepository,
                         ForbiddenWordsValidator forbiddenWordsValidator) {
        this.phraseRepository = phraseRepository;
        this.forbiddenWordsValidator = forbiddenWordsValidator;
    }

    public void addPhrase(String phrase)
        throws ForbiddenWordsInPhraseException, PhraseAlreadyExistExcpetion {
        if (phraseRepository.contains(phrase)) {
            throw new PhraseAlreadyExistExcpetion("Phrase "+ phrase+ "already exists");
        }
        if (!forbiddenWordsValidator.validate(phrase)){
            throw new ForbiddenWordsInPhraseException("Phrase "+ phrase + "contains nasty words :D");
        }
        phraseRepository.save(phrase);
        }
    }

