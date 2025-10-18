package calculator;

public class Calculator {

    private final Splitter splitter;

    public Calculator(final Splitter splitter) {
        this.splitter = splitter;
    }

    public int sum(final String input) {
        int num = 0;

        String[] rawValues = splitter.split(input);
        for (String rawValue : rawValues) {
            try {
                num += Integer.parseInt(rawValue);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("구분자와 숫자로 이루어진 문자열이어야 합니다.");
            }
        }

        return num;
    }
}
