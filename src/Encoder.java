public interface Encoder {
    char encode(char c, int key);

    char decode(char c, int key);
}
