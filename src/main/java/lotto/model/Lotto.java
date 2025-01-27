package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int BONUS_NUMBER_CHECK_COUNT = 5;
    private static final int CORRECT_WITH_BONUS_NUMBER = 7;
    private static final String LOTTO_MIN_SIZE_ERROR = "로또 번호는 6개여야 합니다";
    private static final String DUPLICATE_LOTTO_NUMBER_ERROR = "로또 번호는 중복이 될 수 없습니다";

    private final List<Number> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        duplicateValidation(numbers);
        List<Integer> sortedNumberList = sortedNumberList(numbers);
        this.numbers = makeNumbers(sortedNumberList);
    }

    private List<Integer> sortedNumberList(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    private List<Number> makeNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(Number::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        int size = numbers.size();
        if (size != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_MIN_SIZE_ERROR);
        }
    }

    private void duplicateValidation(List<Integer> numbers) {
        int size = new HashSet<>(numbers).size();
        if (size < LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER_ERROR);
        }
    }

    public int countMatchingNumbers(Lotto compareLotto, Number bonusNumber) {
        Set<Number> lottoSet = new HashSet<>(this.numbers);
        Set<Number> answerLottoSet = new HashSet<>(compareLotto.numbers);

        lottoSet.retainAll(answerLottoSet);

        int correctNumberCount = lottoSet.size();

        if (lottoSet.size() == BONUS_NUMBER_CHECK_COUNT && numbers.contains(bonusNumber)) {
            correctNumberCount = CORRECT_WITH_BONUS_NUMBER;
        }

        return correctNumberCount;
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(Number::getNumber)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }
}
