package lotto;

import lotto.randomNumber.LottoNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class LottoMatcherTest {

    @Test
    void 정적_팩토리_메서드가_입력값을_이용해_LottoMatcher를_생성한다() {
        List<Integer> inputNumber = List.of(1, 2, 3, 4, 5, 6);

        LottoMatcher from = LottoMatcher.from(inputNumber);

        Assertions.assertThat(from)
                .extracting("answerLotto")
                .isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 보너스_번호를_입력받아_Number_객체로_전환하여_저장() {
        LottoMatcher matcher = LottoMatcher.from(List.of(1, 2, 3, 4, 5, 6));

        matcher.insertBonusNumber("7");

        Assertions.assertThat(matcher)
                .extracting("bonusNumber")
                .isEqualTo(new Number(7));
    }

    @ParameterizedTest
    @MethodSource("provideLottoListAndAnswerLotto")
    void 제작된_로또_번호와_정답_번호와_맞춰서_몇개_맞췄는지_확인(LottoPaper lottoPaper, Lotto answerLotto, List<Integer> expected) {
        LottoMatcher lottoMatcher = new LottoMatcher(answerLotto);

        Assertions.assertThat(lottoMatcher.countMatchingNumbers(lottoPaper)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLottoListAndAnswerLotto() {
        return Stream.of(
                Arguments.of(
                        new LottoPaper(List.of(
                                new Lotto(List.of(1, 7, 8, 9, 10, 11)),
                                new Lotto(List.of(1, 2, 8, 9, 10, 11)),
                                new Lotto(List.of(1, 2, 3, 9, 10, 11)),
                                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 11)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 6))
                        )),
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                        List.of(1, 2, 3, 4, 5, 6)
                )
        );
    }



}
