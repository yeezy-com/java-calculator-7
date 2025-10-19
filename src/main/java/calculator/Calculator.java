package calculator;

public class Calculator {

    private final Splitter splitter;

    public Calculator(final Splitter splitter) {
        this.splitter = splitter;
    }

    public long sum(final String input) {
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
