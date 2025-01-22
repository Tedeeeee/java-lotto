package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("생성자를 통해 숫자를 받는다")
    void inputNumber() {
        Number number = new Number(10);

        Assertions.assertThat(number.getNumber()).isEqualTo(10);
    }
}
