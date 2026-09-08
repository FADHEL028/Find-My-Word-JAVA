import java.io.IOException;
import words.WordSet;
import words.JsonWordSet;

public class WordRepository implements IWordRepository {

    private final WordSet wordSet;

    public WordRepository(String path) throws IOException {
        this.wordSet = new JsonWordSet(path);
    }

    @Override
    public Word getWord() {
        String randomWord = wordSet.random();
        return new Word(randomWord);
    }

    @Override
    public Word getSecretWord() {
        return getWord();
    }

    @Override
    public boolean contains(String word) {
    	for(int i=0; i<wordSet.size(); i++){
    		if (this.wordSet.word(i).equals(word)){
    			return true;
    		}
    	}
        return false;
    }
}
