package lotto.randomNumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

class NextStepLottoNumberGeneratorTest {

    private LottoNumberGenerator generator = new NextStepLottoNumberGenerator();

    @RepeatedTest(50)
    void 번호_갯수는_항상_6개가_나와야_한다() {
        List<Integer> generate = generator.generate();

        Assertions.assertThat(generate).hasSize(6);
    }

    @Test
    void 번호의_범위는_1_에서_45_사이여야_한다() {
        List<Integer> generate = generator.generate();

        Assertions.assertThat(generate)
                .allMatch((number) -> number >= 1 && number <= 45);
    }

    @Test
    void 번호의_중복은_허용하지_않는다() {
        List<Integer> generate = generator.generate();

        Assertions.assertThat(generate).doesNotHaveDuplicates();
    }

    @RepeatedTest(20)
    void 여러_번_호출_시_랜덤성이_보장되어야_한다() {
        List<Integer> first = generator.generate();
        List<Integer> second = generator.generate();

        Assertions.assertThat(first).isNotEqualTo(second);
    }
}
