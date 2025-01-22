package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {

    @Test
    @DisplayName("생성자를 통해 숫자를 받는다")
    void inputNumber() {
        Number number = new Number(10);

        Assertions.assertThat(number.getNumber()).isEqualTo(10);
    }

    @ParameterizedTest
    @DisplayName("1 ~ 45를 벗어난 숫자는 예외를 발생 시킨다")
    @ValueSource(ints = {0, 46})
    void numberRangeCheck(int rangeValue) {
        Assertions.assertThatThrownBy(() -> new Number(rangeValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 ~ 45 사이의 값을 입력해주세요");
    }

    @ParameterizedTest
    @DisplayName("1 ~ 45 사이의 숫자는 예외를 던지지 않는다")
    @ValueSource(ints = {1, 45})
    void numberRangeCheckNotThrowException(int rangeValue) {
        Assertions.assertThatCode(() -> new Number(rangeValue))
                .doesNotThrowAnyException();
    }
}
