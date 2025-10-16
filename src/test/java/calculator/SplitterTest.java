package calculator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// 쉼표(,), 콜론(:)을 구분자로 가지는 문자열을 분리할 수 있다.
public class SplitterTest {

    @Test
    void 쉼표로_된_문자열을_구분할_수_있다() {
        Splitter splitter = new Splitter();
        String input = "f,3,2";

        var output = splitter.split(input);

        String[] expected = input.split(",");
        assertThat(output).hasSize(3);
        assertThat(output).contains(expected);
    }
}
