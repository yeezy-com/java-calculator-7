package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {

    private String delimiters = ",|:";

    public String[] split(final String input) {
        validateInputFormat(input);
        List<String> strings = splitToDelimiter(input);
        return getArrayFrom(strings);
    }

    private List<String> splitToDelimiter(final String input) {
        List<String> strings = new ArrayList<>();

        String[] splittedInput = input.split(delimiters);
        for (String splittedPart : splittedInput) {
            strings.add(splittedPart);
        }
        return strings;
    }

    public void addDelimiter(final String custom) {
        delimiters += "|" + custom;
    }

    private void validateInputFormat(final String input) {
        validateIsNotContinuousDelimiter(input);
        validateIsNotStartWithDelimiter(input);
        validateContainsDelimiter(input);
        validateOnlyUsingColonOrComma(input);
    }

    private void validateIsNotContinuousDelimiter(final String input) {
        if (input.matches(".*[^a-zA-Z0-9]{2,}.*")) {
            throw new IllegalArgumentException("구분자는 연속해서 올 수 없습니다.");
        }
    }

    private void validateIsNotStartWithDelimiter(final String input) {
        if (input.matches("^[^a-zA-Z0-9].*")) {
            throw new IllegalArgumentException("구분자로 시작할 수 없습니다.");
        }
    }

    private void validateOnlyUsingColonOrComma(final String input) {
        if (!input.matches("[0-9a-zA-Z,:]+")) {
            throw new IllegalArgumentException("쉼표(,)와 콜론(:)만 구분자로 사용할 수 있습니다.");
        }
    }

    private void validateContainsDelimiter(final String input) {
        if (!(input.contains(",") || input.contains(":"))) {
            throw new IllegalArgumentException("문자열에 구분자가 포함되어야 합니다.");
        }
    }

    private String[] getArrayFrom(final List<String> strings) {
        String[] ans = new String[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            ans[i] = strings.get(i);
        }
        return ans;
    }

    private String extractCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("//([^0-9])\\n");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
