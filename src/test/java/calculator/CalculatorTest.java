package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

    @ParameterizedTest
    @MethodSource(value = "sumOfStrings")
    void 구분한_문자열의_합을_반환한다(final String input, final int ans) {
        final var calculator = new Calculator(new Splitter());

        final var sum = calculator.sum(input);

        assertThat(sum).isEqualTo(ans);
    }

    private static Stream<Arguments> sumOfStrings() {
        return Stream.of(
            Arguments.of("4:3:2", 9),
            Arguments.of("4,4,2,2", 12),
            Arguments.of("4,1", 5),
            Arguments.of("1,2,3:4:5", 15)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"4:5:f", "f:f:d", "fas:fda:3"})
    void 숫자가_아닌_값이_있으면_예외가_발생한다(final String input) {
        final var calculator = new Calculator(new Splitter());

        assertThatThrownBy(() -> calculator.sum(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("구분자와 숫자로 이루어진 문자열이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0:2:3", "1,0,4", "3:0:4", "0:0:0"})
    void 숫자0은_예외가_발생한다(final String input) {
        final var calculator = new Calculator(new Splitter());

        assertThatThrownBy(() -> calculator.sum(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("0은 입력할 수 없습니다.");
    }

    @Test
    void 숫자가_매우_클_수_있다() {
        final var calculator = new Calculator(new Splitter());
        final var input = "10000000000:1";

        final var output = calculator.sum(input);

        assertThat(output).isEqualTo(10000000001L);
    }

    @Test
    void UnsignedLong타입을_넘어서는_숫자는_예외가_발생한다() {
        final var calculator = new Calculator(new Splitter());
        final var input = "4294967297:1";

        assertThatThrownBy(() -> calculator.sum(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("하나의 숫자 혹은 합은 4,294,967,295 이하여야 합니다.");
    }
}
