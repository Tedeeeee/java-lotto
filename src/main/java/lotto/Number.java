package lotto;

import java.util.Objects;

public class Number {
    private static final String INVALID_NUMBER_MESSAGE = "1 ~ 45 사이의 값을 입력해주세요";
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public Number(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int inputNumber) {
        if (inputNumber < MIN_NUMBER || inputNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object compare) {
        if (this == compare) {
            return true;
        }
        if (!(compare instanceof Number compareNumber)) {
            return false;
        }
        return number == compareNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
