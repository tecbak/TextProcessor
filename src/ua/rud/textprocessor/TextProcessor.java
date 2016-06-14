package ua.rud.textprocessor;

import java.io.*;
import java.text.BreakIterator;
import java.util.*;

public class TextProcessor {
    private List<Sentence> sentences = new ArrayList<>();

    /*Constructor*/
    public TextProcessor() {
    }

    /*Methods*/
    public void readTextFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        sentences = readText(reader);
    }

    private List<Sentence> readText(BufferedReader reader) throws IOException {
        List<Sentence> list = new ArrayList<>();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            line = line.replaceAll("\\s{2,}|\\t|\\u00A0", " ");
            list.addAll(splitText(line + "\n"));
        }
        return list;
    }

    private List<Sentence> splitText(String text) {
        List<Sentence> sentences = new ArrayList<>();
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(new Locale("ru"));
        breakIterator.setText(text);

        for (int start = breakIterator.first(), end = breakIterator.next();
             end != breakIterator.DONE;
             start = end, end = breakIterator.next()) {
            String sentence = text.substring(start, end);
            sentences.add(new Sentence(sentence));
        }
        return sentences;
    }

    @Override
    public String toString() {
        return getText();
    }

    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (Sentence sentence : sentences) {
            builder.append(sentence.getText());
        }
        return builder.toString();
    }

    public List<String> sentencesWithDuplicatedWords() {
        List<String> list = new ArrayList<>();
        for (Sentence sentence : sentences) {
            if (sentence.hasDuplicateWords()) {
                list.add(sentence.getText());
            }
        }
        return list;
    }

    public List<String> sentencesOrderedByNumberOfWords() {
        List<String> list = new ArrayList<>();
        PriorityQueue<Sentence> queue = new PriorityQueue<>(new Comparator<Sentence>() {
            @Override
            public int compare(Sentence o1, Sentence o2) {
                return o1.numberOfWords() - o2.numberOfWords();
            }
        });

        queue.addAll(this.sentences);

        while (!queue.isEmpty())
            list.add(queue.remove().getText());

        return list;
    }
}
