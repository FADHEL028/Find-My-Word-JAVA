public class FixedWordRepository implements IWordRepository {

    private Word fixedWord;

    public FixedWordRepository(String word) {
        if (word == null) {
            
            this.fixedWord = new Word("abces");
        } else {
            this.fixedWord = new Word(word);
        }
    }

    @Override
    public Word getWord() {
        return fixedWord;
    }

    @Override
    public Word getSecretWord() {
        return fixedWord;
    }

    @Override
    public boolean contains(String word) {
        if (word == null) return false;
        return fixedWord.getValue().equalsIgnoreCase(word);
    }
}