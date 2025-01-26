package lotto;

import lotto.randomNumber.LottoNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoPaperTest {
    private final LottoNumberGenerator generator = new FixedLottoNumberGenerator();

    @ParameterizedTest
    @CsvSource(value = {"1000, 1", "2000, 2"})
    void 원하는_금액에_맞는_로또_번호_갯수와_리스트의_사이즈는_같아야_한다(String money, int expected) {
        LottoPaper withCount = LottoPaper.createWithCount(money, generator);

        Assertions.assertThat(withCount.getLottos()).hasSize(expected);
    }
}
