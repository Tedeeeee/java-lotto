package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        duplicateValidation(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void duplicateValidation(List<Integer> numbers) {
        Set<Integer> duplicates = new HashSet<>(numbers);

        if (duplicates.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복이 될 수 없습니다");
        }
    }
}
