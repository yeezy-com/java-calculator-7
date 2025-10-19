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
            long num = parseToInt(rawValue);
            validateIsPositiveNumber(num);
            sum += num;
        }

        return sum;
    }

    private long parseToInt(final String rawValue) {
        try {
            return Long.parseLong(rawValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("구분자와 숫자로 이루어진 문자열이어야 합니다.");
        }
    }

    private void validateIsPositiveNumber(long num) {
        if (num == 0) {
            throw new IllegalArgumentException("0은 입력할 수 없습니다.");
        }
    }
}
