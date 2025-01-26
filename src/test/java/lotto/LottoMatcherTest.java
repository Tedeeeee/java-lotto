package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoMatcherTest {

    @Test
    void 정적_팩토리_메서드가_입력값을_이용해_LottoMatcher를_생성한다() {
        List<Integer> inputNumber = List.of(1, 2, 3, 4, 5, 6);

        LottoMatcher from = LottoMatcher.from(inputNumber);

        Assertions.assertThat(from)
                .extracting("answerLotto")
                .isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }
}
