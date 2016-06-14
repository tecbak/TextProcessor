package ua.rud;

import ua.rud.textprocessor.TextProcessor;

import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
        TextProcessor processor = new TextProcessor();
        processor.readTextFromFile("Prestuplenie_i_nakazanie.txt");

//        System.out.println(processor);

        List<String> list = processor.sentencesWithDuplicatedWords();
        printList(list);

//        list = processor.sentencesOrderedByNumberOfWords();
//        printList(list);
    }

    private static <E> void printList(List<E> list) {
        for (E e : list) {
            System.out.println(e);
        }
    }
}
