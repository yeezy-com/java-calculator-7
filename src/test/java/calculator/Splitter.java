package calculator;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    public String[] split(final String input) {
        if (!(input.contains(",") || input.contains(":"))) {
            throw new IllegalArgumentException();
        }
        List<String> strings = splitOfColonAndComma(input);
        String[] ans = getArrayFrom(strings);
        return ans;
    }

    private String[] getArrayFrom(List<String> strings) {
        String[] ans = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            ans[i] = strings.get(i);
        }
        return ans;
    }

    private List<String> splitOfColonAndComma(String input) {
        List<String> strings = new ArrayList<>();

        String[] split = input.split(",");
        for (String s : split) {
            for (String tmp : s.split(":")) {
                strings.add(tmp);
            }
        }
        return strings;
    }
}
