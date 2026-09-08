
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

   private Word secretWord;
   private List<Attempt> attempts;
   private static final int MAX_ATTEMPTS = 6;
   private IWordRepository repository;
   private ConsoleView view;

   public void start() throws IOException {
      
      repository = new WordRepository("data/mots.json");
      secretWord = repository.getSecretWord();
      attempts = new ArrayList<>();
      view = new ConsoleView(secretWord.length(), repository);

      while (attempts.size() < MAX_ATTEMPTS) {
         String guess = view.getPlayerGuess();
         Attempt attempt = analyze(new Word(guess));
         attempts.add(attempt);
         view.displayAttemptResult(attempt);

         if (attempt.isCorrect()) {
            view.displayWinMessage();
            return;
         }
      }
      view.displayLoseMessage(secretWord);
   }
   public void playTurn() {
        String guess = view.getPlayerGuess();
        Attempt attempt = analyze(new Word(guess));
        attempts.add(attempt);
        view.displayAttemptResult(attempt);
   }
   public Attempt analyze(Word word){
         int len = word.length();
         LetterResult[] results = new LetterResult[len];

         
         for (int i = 0; i < len; i++) {
            if (word.charAt(i) == secretWord.charAt(i)) {
               results[i] = LetterResult.OK;
            } else {
               results[i] = null; 
            }
         }

         Map<Character, Integer> remaining = new HashMap<>();
         for (int i = 0; i < len; i++) {
            if (results[i] != LetterResult.OK) {
               char c = secretWord.charAt(i);
               remaining.put(c, remaining.getOrDefault(c, 0) + 1);
            }
         }

         
         for (int i = 0; i < len; i++) {
            if (results[i] == LetterResult.OK) continue;
            char guessChar = word.charAt(i);
            int count = remaining.getOrDefault(guessChar, 0);
            if (count > 0) {
               results[i] = LetterResult.PRESENT;
               remaining.put(guessChar, count - 1);
            } else {
               results[i] = LetterResult.ABSENT;
            }
         }

         return new Attempt(word, results);
   }
   public boolean isGameOver() {
      return attempts.size() >= MAX_ATTEMPTS || attempts.stream().anyMatch(Attempt::isCorrect);
   }
   public boolean isGameWon() {
      return attempts.stream().anyMatch(Attempt::isCorrect);
   }

 

}