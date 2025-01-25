package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호의_개수가_6개보다_적다면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Number를_6개_가진_로또_번호가_만들어진다() {
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(validNumbers);

        List<Number> validate = validNumbers.stream().map(Number::new).toList();
        Assertions.assertThat(lotto.getNumbers()).isEqualTo(validate);
    }

    @Test
    void 로또의_6개의_숫자는_오름차순으로_정렬되어야_한다() {
        List<Integer> descValidNumbers = List.of(6, 5, 4, 3, 2, 1);

        Lotto lotto = new Lotto(descValidNumbers);

        List<Integer> ascValidNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Number> validate = ascValidNumbers.stream().map(Number::new).toList();

        Assertions.assertThat(lotto.getNumbers()).isEqualTo(validate);
    }
}
