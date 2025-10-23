package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {

    private String delimiters = ",:";

    public String[] split(final String input) {
        String customDelimiter = extractCustomDelimiter(input);
        addDelimiter(customDelimiter);

        String realInput = extractRestInput(input, customDelimiter);

        validateInputFormat(realInput);
        List<String> strings = splitToDelimiter(realInput);
        return getArrayFrom(strings);
    }

    private String extractCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("//(.)\\n");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String extractRestInput(final String input, final String customDelimiter) {
        if (customDelimiter != null) {
            if (customDelimiter.matches("[0-9]")) {
                throw new IllegalArgumentException("커스텀 구분자는 숫자일 수 없습니다.");
            }

            String[] split = input.split("\\n");
            validateNotOnlyCustomDelimiter(split);
            String restInput = split[1];
            validateCustomDelimiterApplyOverTwice(restInput);

            return restInput;
        }

        return input;
    }

    private void validateNotOnlyCustomDelimiter(String[] split) {
        if (split.length <= 1) {
            throw new IllegalArgumentException("커스텀 구분자만 입력할 수 없습니다.");
        }
    }

    private void validateCustomDelimiterApplyOverTwice(String restInput) {
        if (restInput.startsWith("//")) {
            throw new IllegalArgumentException("커스텀 구분자는 두 번 이상 등록할 수 없습니다.");
        }
    }

    private List<String> splitToDelimiter(final String input) {
        List<String> strings = new ArrayList<>();

        String[] splittedInput = input.split("[" + delimiters + "]");
        for (String splittedPart : splittedInput) {
            strings.add(splittedPart);
        }
        return strings;
    }

    private void addDelimiter(final String custom) {
        if (custom == null) {
            return;
        }
        delimiters += custom;
    }

    private void validateInputFormat(final String input) {
        validateIsNotContinuousDelimiter(input);
        validateIsNotStartWithDelimiter(input);
        validateOnlyUsingDelimiters(input);
        validateContainsDelimiter(input);
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
    private void validateOnlyUsingDelimiters(final String input) {
        if (!input.matches("[0-9a-zA-Z" + delimiters + "]+")) {
            throw new IllegalArgumentException("쉼표(,), 콜론(:), 커스텀 구분자 외에 구분자는 사용할 수 없습니다.");
        }
    }

    private void validateContainsDelimiter(final String input) {
        Pattern pattern = Pattern.compile("[" + delimiters + "]");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
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
}
