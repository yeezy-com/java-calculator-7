package calculator;

public class Calculator {

    private final Splitter splitter;

    public Calculator(final Splitter splitter) {
        this.splitter = splitter;
    }

    public int sum(final String input) {
        int num = 0;

        String[] split = splitter.split(input);
        for (String tmp : split) {
            num += Integer.parseInt(tmp);
        }

        return num;
    }
}
