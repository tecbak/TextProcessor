package ua.rud.textprocessor;

import java.util.*;

class WordFactory {
    private final static WordFactory instance = new WordFactory();
    private Map<String,Word> pool = new HashMap<>();

    /*Constructor*/
    private WordFactory() {
    }

    /*Methods*/
    static WordFactory getInstance() {
        return instance;
    }

    Word create(String item) {


        if (pool.containsKey(item)){
            return pool.get(item);
        } else {
            Word word = new Word(item);
            pool.put(item,word);
            return word;
        }
    }
}
