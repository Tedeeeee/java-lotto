package lotto;

import lotto.model.LottoPrizeCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class LottoPrizeCalculatorTest {

    @Test
    void 수익률을_계산하기_위해_사용자가_입력한_금액을_통한_생성자_테스트() {
        Assertions.assertThat(new LottoPrizeCalculator("5"))
                .extracting("amount")
                .isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("provideYields")
    void 맵형태로_묶인_결과를_통해_가격을_계산하고_수익률_반환(String inputAmount,Map<String, Integer> lottoResult, double expected) {
        LottoPrizeCalculator calculator = new LottoPrizeCalculator(inputAmount);

        Assertions.assertThat(calculator.calculatePrize(lottoResult)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideYields() {
        return Stream.of(
                Arguments.of(
                        "1000",
                        Map.of(
                                "3개 일치 (5,000원)", 1,
                                "4개 일치 (50,000원)", 1,
                                "5개 일치 (1,500,000원)", 1,
                                "5개 일치, 보너스 볼 일치 (30,000,000원)", 0,
                                "6개 일치 (2,000,000,000원)", 1
                        ),
                        200155500.0
                ),
                Arguments.of(
                        "5000",
                        Map.of(
                                "3개 일치 (5,000원)", 1,
                                "4개 일치 (50,000원)", 0,
                                "5개 일치 (1,500,000원)", 0,
                                "5개 일치, 보너스 볼 일치 (30,000,000원)", 0,
                                "6개 일치 (2,000,000,000원)", 0
                        ),
                        100.0
                ),
                Arguments.of(
                        "10000",
                        Map.of(
                                "3개 일치 (5,000원)", 0,
                                "4개 일치 (50,000원)", 2,
                                "5개 일치 (1,500,000원)", 1,
                                "5개 일치, 보너스 볼 일치 (30,000,000원)", 0,
                                "6개 일치 (2,000,000,000원)", 0
                        ),
                        16000.0
                ),
                Arguments.of(
                        "2000",
                        Map.of(
                                "3개 일치 (5,000원)", 0,
                                "4개 일치 (50,000원)", 0,
                                "5개 일치 (1,500,000원)", 0,
                                "5개 일치, 보너스 볼 일치 (30,000,000원)", 0,
                                "6개 일치 (2,000,000,000원)", 0
                        ),
                        0.0
                ),
                Arguments.of(
                        "15000",
                        Map.of(
                                "3개 일치 (5,000원)", 0,
                                "4개 일치 (50,000원)", 0,
                                "5개 일치 (1,500,000원)", 0,
                                "5개 일치, 보너스 볼 일치 (30,000,000원)", 2,
                                "6개 일치 (2,000,000,000원)", 0
                        ),
                        400000.0
                )
        );
    }
}
