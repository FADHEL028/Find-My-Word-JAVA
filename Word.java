import java.util.HashSet;
import java.util.Set;
public class Word {
    private String value;
    public Word(String value) {
        this.value = value;   
    }
    public String getValue() {
        return this.value;
    }
    public int length() {
        return value.length();
    }
    public char charAt(int index) {
        return value.charAt(index);
    }
    public boolean isValid() {
        return value.matches("[a-zA-Z]+");
    }
    public boolean hasUniqueLetters() {
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : value.toCharArray()) {
            if (!uniqueChars.add(c)) {
                return false; 
            }
        }
        return true; 
    }
    public boolean containsLetter(char letter) {
        return value.indexOf(letter) >= 0;
    }
}
