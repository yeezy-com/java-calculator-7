package calculator;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    public String[] split(final String input) {
        if (input.matches(".*[,:]{2,}.*")) {
            throw new IllegalArgumentException();
        }
        validateIsNotStartWithDelimiter(input);
        validateContainsColonOrComma(input);
        validateOnlyUsingColonOrComma(input);
        List<String> strings = splitOfColonAndComma(input);
        return getArrayFrom(strings);
    }

    private void validateIsNotStartWithDelimiter(String input) {
        if (input.startsWith(":") || input.startsWith(",")) {
            throw new IllegalArgumentException("구분자로 시작할 수 없습니다.");
        }
    }

    private void validateOnlyUsingColonOrComma(String input) {
        if (!input.matches("[0-9a-zA-Z-,:]+")) {
            throw new IllegalArgumentException("쉼표(,)와 콜론(:)만 구분자로 사용할 수 있습니다.");
        }
    }

    private void validateContainsColonOrComma(String input) {
        if (!(input.contains(",") || input.contains(":"))) {
            throw new IllegalArgumentException("문자열에 구분자가 포함되어야 합니다.");
        }
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
