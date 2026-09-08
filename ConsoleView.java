import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private static final int MAX_ATTEMPTS = 6;
    private final int wordLength;
    private final IWordRepository repository;
    private final List<Attempt> attempts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);


    public ConsoleView(int wordLength, IWordRepository repository) {
        this.wordLength = wordLength;
        this.repository = repository;
    }

    public String getPlayerGuess() {
        while (true) {
            System.out.print("Enter your guess (" + wordLength + " letters): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.length() != wordLength) {
                System.out.println("Please enter exactly " + wordLength + " letters.");
                continue;
            }
            if (!input.matches("[a-zA-Z]+")) {
                System.out.println("Please use only letters.");
                continue;
            }
            if (!repository.contains(input)) {
                System.out.println("Word not in dictionary. Try another word.");
                continue;
            }
            return input;
        }
    }

    public void displayAttemptResult(Attempt attempt) {
        attempts.add(attempt);
        renderGrid();
    }

    private void renderGrid() {
        System.out.println();
        for (int row = 0; row < MAX_ATTEMPTS; row++) {
            if (row < attempts.size()) {
                Attempt a = attempts.get(row);
                LetterResult[] results = a.getResults();
                String word = a.getWord().getValue();
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < wordLength; i++) {
                    char ch = word.charAt(i);
                    LetterResult res = results[i];
                    switch (res) {
                        case OK:
                            line.append(Character.toUpperCase(ch)).append("(OK)");
                            break;
                        case PRESENT:
                            line.append(Character.toUpperCase(ch)).append("(PRESENT)");
                            break;
                        case ABSENT:
                        default:
                            line.append(Character.toUpperCase(ch)).append("(ABSENT)");
                            break;
                    }
                    if (i < wordLength - 1) line.append(" ");
                }
                System.out.println(line);
            } else {
                
                StringBuilder empty = new StringBuilder();
                for (int i = 0; i < wordLength; i++) {
                    empty.append("[ _ ]");
                    if (i < wordLength - 1) empty.append(" ");
                }
                System.out.println(empty);
            }
        }
        System.out.println();
    }

    public void displayWinMessage() {
        System.out.println("Congratulations! You guessed the word!");
    }

    public void displayLoseMessage(Word secretWord) {
        System.out.println("Game over. The secret word was: " + secretWord.getValue());
    }
}
