package ua.rud.textprocessor;

import java.util.ArrayList;
import java.util.List;

final class Word extends SentenceItem {
    //    private final List<Letter> letters;
    private final String word;

    /*Constructor*/
    Word(String word) {
        this.word = word;
    }

    /*Methods*/
    final String getText() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word != null ? word.equals(word1.word) : word1.word == null;

    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
    }
}
