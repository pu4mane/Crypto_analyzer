package io.pu4mane;

import java.util.Map;

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

    @Override
    public int bruteForceKey(String text, String exampleText) {
        int key = 0;
        int minValueDifference = Integer.MAX_VALUE;
        Map<Character, Integer> exampleTextCharHistograms = StaticAnalyzer.getCharHistograms(exampleText, firstAlphabetLetter, lastAlphabetLetter);
        for (int shift = 1; shift < totalAlphabetLetters; shift++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : text.toCharArray()) {
                stringBuilder.append(decode(c, shift));
            }
            Map<Character, Integer> textCharHistograms = StaticAnalyzer.getCharHistograms(stringBuilder.toString(), firstAlphabetLetter, lastAlphabetLetter);
            int currentValueDifference = StaticAnalyzer.compareHistograms(exampleTextCharHistograms, textCharHistograms);
            key = (currentValueDifference < minValueDifference) ? shift : key;
            minValueDifference = Math.min(currentValueDifference, minValueDifference);
        }
        return key;
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
