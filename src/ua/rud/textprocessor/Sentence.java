package ua.rud.textprocessor;

import java.text.BreakIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Sentence {
    private static final SentenceItemFactory factory = SentenceItemFactory.getInstance();
    private final List<SentenceItem> items;

    /*Constructor*/
    Sentence(String sentence) {
        items = splitSentenceItems(sentence);
    }

    /*Methods*/
    final List<SentenceItem> splitSentenceItems(String sentence) {
        List<SentenceItem> items = new ArrayList<>();
        BreakIterator breakIterator = BreakIterator.getWordInstance(new Locale("ru"));
        breakIterator.setText(sentence);

        for (int start = breakIterator.first(), end = breakIterator.next();
             end != breakIterator.DONE;
             start = end, end = breakIterator.next()) {
            String item = sentence.substring(start, end);
            items.add(createSentenceItem(item));
        }
        return items;
    }

    private SentenceItem createSentenceItem(String item) {
        return factory.create(item);
//        Matcher matcher;
//
//        Pattern space = Pattern.compile("\\s");
//        matcher = space.matcher(item);
//        if (matcher.matches()) {
//            return Space.getInstance();
//        }
//
//        Pattern punctuation = Pattern.compile("[^0-9a-zA-Zа-яА-ЯёЁ\\s]"); //^\W\S   [^0-9a-zA-Zа-яА-Я\s]
//        matcher = punctuation.matcher(item);
//        if (matcher.matches()) {
//            return new Punctuation(item);
//        }
//
//        return new Word(item);
    }

    public final String getText() {
        StringBuilder builder = new StringBuilder();
        for (SentenceItem item : items) {
            builder.append(item.getText());
        }
        return builder.toString();
    }

    final boolean hasDuplicateWords() {
        Set<Word> words = new HashSet<>();
        for (SentenceItem item : items) {
            if (item instanceof Word && !words.add((Word) item)) {
                return true;
            }
        }
        return false;
    }

    final int numberOfWords() {
        int n = 0;
        for (SentenceItem item : items) {
            if (item instanceof Word) {
                n++;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        return getText();
    }
}
