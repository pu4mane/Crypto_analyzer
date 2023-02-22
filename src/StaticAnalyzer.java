import java.util.HashMap;
import java.util.Map;

public class StaticAnalyzer {
    public Map<Character, Integer> getCharHistograms(String text, char rangeFrom, char rangeTo) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.toLowerCase().charAt(i);
            if (c >= rangeFrom && c <= rangeTo) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }
        return map;
    }

    public int compareHistograms(Map<Character, Integer> h1, Map<Character, Integer> h2) {
        int result = 0;
        for (Map.Entry<Character, Integer> h1Letter : h1.entrySet()) {
            for (Map.Entry<Character, Integer> h2Letter : h2.entrySet()) {
                if (h1Letter.getKey().equals(h2Letter.getKey())) {
                    result += Math.abs(h1Letter.getValue() - h2Letter.getValue());
                }
            }
        }
        return result;
    }

    private Map<Character, Integer> normalize(Map<Character, Integer> map) {
        int max = 0;
        for (Map.Entry<Character, Integer> letter : map.entrySet()) {
            max = (letter.getValue() > max) ? letter.getValue() : max;
        }
        double divisor = max / 100.0;
        Map<Character, Integer> result = new HashMap<>();
        for (Map.Entry<Character, Integer> letter : map.entrySet()) {
            int normalizeValue = (int) (letter.getValue() / divisor);
            result.put(letter.getKey(), normalizeValue);
        }
        return result;
    }
}
