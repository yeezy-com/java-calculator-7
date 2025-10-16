package calculator;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    public String[] split(final String input) {
        List<String> strings = new ArrayList<>();

        String[] split = input.split(",");
        for (String s : split) {
            for (String tmp : s.split(":")) {
                strings.add(tmp);
            }
        }

        String[] ans = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            ans[i] = strings.get(i);
        }
        return ans;
    }
}
