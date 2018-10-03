package pl.sda.hangman.domain;

import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

public class ForbiddenWordsValidator {
    private ForbiddenWordsRepository forbiddenWordsRepository;

    public ForbiddenWordsValidator(ForbiddenWordsRepository forbiddenWordsRepository) {
        this.forbiddenWordsRepository = forbiddenWordsRepository;
    }


    public boolean validate (String phrase){
        String preparePhrase = preparePhrase(phrase);
        return forbiddenWordsRepository.findAll()
                .stream()
                .map(e-> preparePhrase(e))
                .noneMatch(e -> preparePhrase.contains(e));
    }

    private String preparePhrase(String phrase){
        return phrase.replaceAll(" ", "")
                .toLowerCase();
    }
}
