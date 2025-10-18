package calculator;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

    public String[] split(final String input) {
        validateInputFormat(input);
        List<String> strings = splitOfColonAndComma(input);
        return getArrayFrom(strings);
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

        String[] splittedInput = input.split(",");
        for (String splittedPart : splittedInput) {
            for (String part : splittedPart.split(":")) {
                strings.add(part);
            }
        }
        return strings;
    }

    private void validateInputFormat(String input) {
        validateIsNotContinuousDelimiter(input);
        validateIsNotStartWithDelimiter(input);
        validateContainsColonOrComma(input);
        validateOnlyUsingColonOrComma(input);
    }

    private void validateIsNotContinuousDelimiter(String input) {
        if (input.matches(".*[^a-zA-Z0-9]{2,}.*")) {
            throw new IllegalArgumentException("구분자는 연속해서 올 수 없습니다.");
        }
    }

    private void validateIsNotStartWithDelimiter(String input) {
        if (input.matches("^[^a-zA-Z0-9].*")) {
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
}
