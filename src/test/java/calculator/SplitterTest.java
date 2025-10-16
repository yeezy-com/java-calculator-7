package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// 쉼표(,), 콜론(:)을 구분자로 가지는 문자열을 분리할 수 있다.
public class SplitterTest {

    @ParameterizedTest
    @ValueSource(strings = {"4,3,2", "1,2,3,4", "2,3,4,6", "2,65,2,53"})
    void 쉼표로_된_문자열을_구분할_수_있다(final String input) {
        var splitter = new Splitter();

        var output = splitter.split(input);

        String[] expected = input.split(",");
        assertThat(output).hasSize(expected.length);
        assertThat(output).contains(expected);
    }

    @Test
    void 콜론으로_된_문자열을_구분할_수_있다() {
        var splitter = new Splitter();
        var input = "f:3:2";

        var output = splitter.split(input);

        String[] expected = input.split(":");
        assertThat(output).hasSize(3);
        assertThat(output).contains(expected);
    }

    @Test
    void 쉼표나_콜론이_아닌_구분자는_구분할_수_없다() {
        var splitter = new Splitter();
        var input = "1.3.2";

        assertThatThrownBy(() -> splitter.split(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
