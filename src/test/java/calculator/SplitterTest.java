package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// 쉼표(,), 콜론(:)을 구분자로 가지는 문자열을 분리할 수 있다.
public class SplitterTest {

    @ParameterizedTest
    @ValueSource(strings = {"4,3,2", "1,2,3,4", "2,3,4,6", "2,65,2,53"})
    void 쉼표로_된_문자열을_구분할_수_있다(final String input) {
        final var splitter = new Splitter();

        final var output = splitter.split(input);

        String[] expected = input.split(",");
        assertThat(output).hasSize(expected.length);
        assertThat(output).contains(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f:3:2", "3:5:1", "a:b:c"})
    void 콜론으로_된_문자열을_구분할_수_있다(final String input) {
        final var splitter = new Splitter();

        final var output = splitter.split(input);

        String[] expected = input.split(":");
        assertThat(output).hasSize(expected.length);
        assertThat(output).contains(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.3.2", "1.3:2", "1,3.2", "1-4:3"})
    void 쉼표나_콜론이_아닌_구분자는_구분할_수_없다(final String input) {
        final var splitter = new Splitter();

        assertThatThrownBy(() -> splitter.split(input))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "45", "23", "fd"})
    void 구분자가_없는_문자열에_대해서_예외를_발생한다(final String input) {
        final var splitter = new Splitter();

        assertThatThrownBy(() -> splitter.split(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("문자열에 구분자가 포함되어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {":3", ",4"})
    void 구분자는_맨_앞에_올_수_없다(final String input) {
        final var splitter = new Splitter();

        assertThatThrownBy(() -> splitter.split(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("구분자로 시작할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"3::3", "3:,3", "3:::3:4"})
    void 구분자는_한_개_이상_연속할_수_없다(final String input) {
        final var splitter = new Splitter();

        assertThatThrownBy(() -> splitter.split(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("구분자는 연속해서 올 수 없습니다.");
    }
}
