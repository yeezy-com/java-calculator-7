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
                try {
                    long num = parseToUnsignedLong(rawValue);
                    validateNumberIsNotOver(num);
                    validateIsPositiveNumber(num);
                    sum += num;
                    validateNumberIsNotOver(sum);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("하나의 숫자 혹은 합은 9,223,372,036,854,775,807 이하여야 합니다.");
                }
                continue;
            }

            throw new IllegalArgumentException("숫자만 계산할 수 있습니다.");
        }

        return sum;
    }

    private long parseToUnsignedLong(final String rawValue) {
        return Long.parseUnsignedLong(rawValue);
    }

    private void validateNumberIsNotOver(long num) {
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
