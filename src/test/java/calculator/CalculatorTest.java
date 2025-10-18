package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void 구분한_문자열의_합을_반환한다() {
        var calculator = new Calculator(new Splitter());
        var input = "4:3:2";

        var sum = calculator.sum(input);

        assertThat(sum).isEqualTo(9);
    }

    @Test
    void 구분한_문자열의_합을_반환한다2() {
        var calculator = new Calculator(new Splitter());
        var input = "4,4,2,2";

        var sum = calculator.sum(input);

        assertThat(sum).isEqualTo(12);
    }
}
