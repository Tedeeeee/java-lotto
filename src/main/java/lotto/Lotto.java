package lotto;

import java.util.*;

public class Lotto {
    private final List<Number> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        duplicateValidation(numbers);
        this.numbers = makeNumbers(numbers);
    }

    private List<Number> makeNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(Number::new)
                .toList();
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

    public List<Number> getNumbers() {
        return numbers;
    }
}
