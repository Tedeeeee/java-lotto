package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoPrizeCalculatorTest {

    @Test
    void 수익률을_계산하기_위해_사용자가_입력한_금액을_통한_생성자_테스트() {
        Assertions.assertThat(new LottoPrizeCalculator("5"))
                .extracting("amount")
                .isEqualTo(5);
    }
}
