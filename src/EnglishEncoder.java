public class EnglishEncoder implements Encoder {
    private static final char firstAlphabetLetter = 'a';
    private static final char firstBigAlphabetLetter = 'A';
    private static final char lastAlphabetLetter = 'z';
    private static final char lastBigAlphabetLetter = 'Z';
    private static final char totalAlphabetLetters = 26;

    @Override
    public char encode(char c, int shift) {
        if (!isAlphabetic(c)) {
            return c;
        } else if (isSmallLetter(c)) {
            return (char) (firstAlphabetLetter + ((c - firstAlphabetLetter + shift) % totalAlphabetLetters));
        } else {
            return (char) (firstBigAlphabetLetter + ((c - firstBigAlphabetLetter + shift) % totalAlphabetLetters));
        }
    }

    @Override
    public char decode(char c, int shift) {
        return encode(c, totalAlphabetLetters - (shift % totalAlphabetLetters));
    }

    public boolean isAlphabetic(char c) {
        return isSmallLetter(c) || isBigLetter(c);
    }

    public boolean isSmallLetter(char c) {
        return (c >= firstAlphabetLetter && c <= lastAlphabetLetter);
    }

    public boolean isBigLetter(char c) {
        return (c >= firstBigAlphabetLetter && c <= lastBigAlphabetLetter);
    }
}
