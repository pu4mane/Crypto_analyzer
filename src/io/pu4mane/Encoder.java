package io.pu4mane;

public interface Encoder {
    char encode(char c, int key);

    char decode(char c, int key);

    int bruteForceKey(String text, String exampleText);
}
