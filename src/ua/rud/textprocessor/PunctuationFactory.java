package ua.rud.textprocessor;

import java.util.HashMap;
import java.util.Map;

class PunctuationFactory {
    private final static PunctuationFactory instance = new PunctuationFactory();
    private Map<String,Punctuation> pool = new HashMap<>();

    /*Constructor*/
    private PunctuationFactory() {
    }

    /*Methods*/
    static PunctuationFactory getInstance() {
        return instance;
    }

    Punctuation create(String item) {
        if (pool.containsKey(item)){
            return pool.get(item);
        } else {
            Punctuation punctuation = new Punctuation(item);
            pool.put(item,punctuation);
            return punctuation;
        }
    }
}
