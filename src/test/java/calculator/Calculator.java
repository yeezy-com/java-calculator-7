package calculator;

public class Calculator {

    private final Splitter splitter;

    public Calculator(final Splitter splitter) {
        this.splitter = splitter;
    }

    public int sum(final String input) {
        int sum = 0;

        String[] rawValues = splitter.split(input);
        for (String rawValue : rawValues) {
            int num = parseToInt(rawValue);
            validateIsPositiveNumber(num);
            sum += num;
        }

        return sum;
    }

    private int parseToInt(final String rawValue) {
        try {
            return Integer.parseInt(rawValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("구분자와 숫자로 이루어진 문자열이어야 합니다.");
        }
    }

    private void validateIsPositiveNumber(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("숫자는 양수로 이루어져야합니다.");
        }
    }
}
