package ua.rud.textprocessor;

final class Punctuation extends SentenceItem {
    private final char symbol;

    /*Constructor*/
    Punctuation(String symbol) {
        this.symbol = symbol.charAt(0);
    }

    /*Methods*/
    final String getText() {
        return Character.toString(symbol);
    }
}
