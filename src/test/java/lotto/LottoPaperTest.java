package lotto;

import lotto.randomNumber.LottoNumberGenerator;
import lotto.randomNumber.NextStepLottoNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class LottoPaperTest {
    private final LottoNumberGenerator generator = new NextStepLottoNumberGenerator();

    @ParameterizedTest
    @CsvSource(value = {"1000, 1", "2000, 2"})
    void 원하는_금액에_맞는_로또_번호_갯수와_리스트의_사이즈는_같아야_한다(String money, int expected) {
        LottoPaper withCount = LottoPaper.createWithCount(money, generator);

        Assertions.assertThat(withCount.getLottos()).hasSize(expected);
    }

    @ParameterizedTest
    @MethodSource("provideLottoMaker")
    void 랜덤_숫자로_생성한_값이_정상적으로_리스트에_추가되는지_확인하는_생성자_테스트(LottoNumberGenerator generator, Lotto lotto) {
        LottoPaper withCount = LottoPaper.createWithCount("1000", generator);

        Assertions.assertThat(withCount.getLottos().get(0)).isEqualTo(lotto);
    }

    private static Stream<Arguments> provideLottoMaker() {
        return Stream.of(
                Arguments.of(
                        (LottoNumberGenerator) () -> List.of(1, 2, 3, 4, 5, 6)
                        ,new Lotto(List.of(1, 2, 3, 4, 5, 6))
                ),
                Arguments.of(
                        (LottoNumberGenerator) () -> List.of(7, 8, 9, 10, 11, 12)
                        ,new Lotto(List.of(7, 8, 9, 10, 11, 12))
                )
        );
    }
}
