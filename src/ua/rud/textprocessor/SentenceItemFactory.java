package ua.rud.textprocessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SentenceItemFactory {
    private final static SentenceItemFactory instance = new SentenceItemFactory();
    private final PunctuationFactory punctuationFactory = PunctuationFactory.getInstance();
    private final WordFactory wordFactory = WordFactory.getInstance();

    private SentenceItemFactory() {
    }

    static SentenceItemFactory getInstance() {
        return instance;
    }

    SentenceItem create(String item) {
        Pattern punctuation = Pattern.compile("\\p{Punct}|\\s|\\n|—|…");
        Matcher matcher = punctuation.matcher(item);

        if (matcher.matches()) {
            return punctuationFactory.create(item);
        } else {
            return wordFactory.create(item);
        }
    }

}
