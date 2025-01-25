package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class LottoMoneyValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1500", "2001", "1999"})
    void 천원단위의_돈이_아니면_예외를_발생(String money) {
        Assertions.assertThatThrownBy(() -> LottoMoneyValidator.validateThousandUnit(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천원 단위의 금액을 입력해주세요");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1000"})
    void 음수의_돈이_들어오면_예외_발생(String money) {
        Assertions.assertThatThrownBy(() -> LottoMoneyValidator.validateThousandUnit(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("양수의 금액을 입력해주세요");
    }

    @ParameterizedTest
    @CsvSource(value = {"1000, 1", "2000, 2"})
    void 정상적인_금액을_입력하면_로또의_갯수를_반환(String money, int lottoCount) {
        Assertions.assertThat(LottoMoneyValidator.validateThousandUnit(money))
                .isEqualTo(lottoCount);
    }


}
