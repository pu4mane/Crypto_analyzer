public class EnglishEncoder implements Encoder {
    private final char firstAlphabetLetter = 'a';
    private final char firstBigAlphabetLetter = 'A';
    private final char totalAlphabetLetters = 26;

    @Override
    public char encode(char c, int shift) {
        if (!isAlphabetLetter(c)) {
            return c;
        } else if (isSmallLetter(c)) {
            return (char) (firstAlphabetLetter + ((c - firstAlphabetLetter + shift) % totalAlphabetLetters));
        } else {
            return (char) (firstBigAlphabetLetter + ((c - firstBigAlphabetLetter + shift) % totalAlphabetLetters));
        }
    }

    @Override
    public char decode(char c, int shift) {
        shift = (totalAlphabetLetters - (shift % totalAlphabetLetters));
        if (!isAlphabetLetter(c)) {
            return c;
        } else if (isSmallLetter(c)) {
            return (char) (firstAlphabetLetter + ((c - firstAlphabetLetter + shift) % totalAlphabetLetters));
        } else {
            return (char) (firstBigAlphabetLetter + ((c - firstBigAlphabetLetter + shift) % totalAlphabetLetters));
        }
    }

    private boolean isAlphabetLetter(char c) {
        return Character.isLetter(c);

    }

    private boolean isSmallLetter(char c) {
        return Character.isLowerCase(c);
    }
}
