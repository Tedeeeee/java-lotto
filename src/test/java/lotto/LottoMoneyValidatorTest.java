package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class LottoMoneyValidatorTest {

    private void assertThrowWithMessageByChangeMoney(String inputMoney, String expectedMessage) {
        Assertions.assertThatThrownBy(() -> LottoMoneyValidator.calculateLottoCount(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    private void assertThrowWithMessageByChangeBonusNumber(String inputBonusNumber, String expectedMessage) {
        Assertions.assertThatThrownBy(() -> LottoMoneyValidator.validateBonusNumber(inputBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    @Nested
    @DisplayName("금액 입력값 검증 테스트")
    class MoneyTest {

        @ParameterizedTest
        @CsvSource(value = {"1000, 1", "2000, 2"})
        void 정상적인_금액을_입력하면_로또의_갯수를_반환(String money, int lottoCount) {
            Assertions.assertThat(LottoMoneyValidator.calculateLottoCount(money))
                    .isEqualTo(lottoCount);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1500", "2001", "1999"})
        void 천원단위의_돈이_아니면_예외를_발생(String money) {
            assertThrowWithMessageByChangeMoney(money, "천원 단위의 금액을 입력해주세요");
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1000", "-1", "-999", "-5000"})
        void 결제_금액이_음수의_돈으로_들어오면_예외_발생(String money) {
            assertThrowWithMessageByChangeMoney(money, "양수의 금액을 입력해주세요");
        }

        @ParameterizedTest
        @ValueSource(strings = {"abcd", "금액", "one", "!@#", "1000원", "천원", "one thousand", "1234,56", "", " "})
        void 결제_금액이_숫자가_아닌_다른_값을_입력하면_예외_발생(String money) {
            assertThrowWithMessageByChangeMoney(money, "정상적인 금액을 입력해주세요");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1000.5", "0.001", "1234.56", "-1000.5"})
        void 결제_금액이_소수_값으로_들어오면_예외_발생(String money) {
            assertThrowWithMessageByChangeMoney(money, "소수점 값은 입력할 수 없습니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "999", "0"})
        void 한개도_사지_않으면_예외_발생(String money) {
            assertThrowWithMessageByChangeMoney(money, "로또를 한 개 이상 구매해주세요");
        }

    }

    @Nested
    @DisplayName("보너스 번호 입력값 검증 테스트")
    class BonusNumberTest {
        @ParameterizedTest
        @ValueSource(strings = {"-1", "-45", "-37"})
        void 보너스_번호가_음수의_값으로_들어오면_예외_발생(String bonusNumber) {
            assertThrowWithMessageByChangeBonusNumber(bonusNumber, "양수의 금액을 입력해주세요");
        }

        @ParameterizedTest
        @ValueSource(strings = {"abcd", "금액", "one", "!@#", "1000원", "천원", "one thousand", "1234,56", "", " "})
        void 보너스_숫자가_숫자가_아닌_다른_값을_입력하면_예외_발생(String bonusNumber) {
            assertThrowWithMessageByChangeBonusNumber(bonusNumber, "정상적인 금액을 입력해주세요");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1.1", "45.1", "23.56"})
        void 보너스_값을_소수_값으로_들어오면_예외_발생(String bonusNumber) {
            assertThrowWithMessageByChangeBonusNumber(bonusNumber, "소수점 값은 입력할 수 없습니다");
        }
    }

}
