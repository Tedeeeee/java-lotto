package lotto;

public class Number {
    private final int number;

    public Number(int number) {
        validNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validNumber(int inputNumber) {
        if (inputNumber < 1 || inputNumber > 45) {
            throw new IllegalArgumentException("1 ~ 45 사이의 값을 입력해주세요");
        }
    }
}
