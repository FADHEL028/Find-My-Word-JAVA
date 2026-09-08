public interface IWordRepository {
    Word getSecretWord();

    Word getWord();

    boolean contains(String word);
}
