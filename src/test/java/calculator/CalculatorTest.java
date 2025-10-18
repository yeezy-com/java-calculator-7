package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
}
