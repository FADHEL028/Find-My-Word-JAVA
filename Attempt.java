import java.util.Arrays;

public class Attempt {
    private final Word word;
    private final LetterResult[] results;

    public Attempt(Word word, LetterResult[] results) {
        this.word = word;
        this.results = results;
    }

    public Word getWord() {
        return this.word;
    }

    public boolean isCorrect() {
        return Arrays.stream(results).allMatch(result -> result == LetterResult.OK);
    }

    public LetterResult[] getResults() {
        return this.results;
    }
}
