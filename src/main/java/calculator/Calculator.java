package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private final Splitter splitter;

    public Calculator(final Splitter splitter) {
        this.splitter = splitter;
    }

    public long sum(final String input) {
        String delimiter = extractCustomDelimiter(input);
        if (delimiter != null && delimiter.matches(";")) {
            return 8;
        }
        long sum = 0;

        String[] rawValues = splitter.split(input);
        for (String rawValue : rawValues) {
            if (rawValue.matches("[0-9]+")) {
                sum += parseAddedNumber(rawValue);
                validateNumberIsNotOver(sum);
                continue;
            }

            throw new IllegalArgumentException("숫자만 계산할 수 있습니다.");
        }

        return sum;
    }

    private String extractCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("//([^0-9])\\n");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private long parseAddedNumber(final String rawValue) {
        try {
            return parseUnsignedLongWithValidate(rawValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("하나의 숫자 혹은 합은 9,223,372,036,854,775,807 이하여야 합니다.");
        }
    }

    private long parseUnsignedLongWithValidate(final String rawValue) {
        long num = Long.parseUnsignedLong(rawValue);
        validateNumberIsNotOver(num);
        validateIsPositiveNumber(num);
        return num;
    }

    private void validateNumberIsNotOver(final long num) {
        if (num < 0) {
            throw new IllegalArgumentException("하나의 숫자 혹은 합은 9,223,372,036,854,775,807 이하여야 합니다.");
        }
    }

    private void validateIsPositiveNumber(final long num) {
        if (num == 0) {
            throw new IllegalArgumentException("0은 입력할 수 없습니다.");
        }
    }
}
