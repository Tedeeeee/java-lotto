package lotto;

public class LottoMoneyValidator {
    private static final int LOTTO_PRICE_UNIT = 1000;

    public static int calculateLottoCount(String inputMoney) {
        int money = parseInt(inputMoney);
        if ((money / LOTTO_PRICE_UNIT) <= 0) {
            throw new IllegalArgumentException("로또를 한 개 이상 구매해주세요");
        }

        if ((money % LOTTO_PRICE_UNIT) > 0) {
            throw new IllegalArgumentException("천원 단위의 금액을 입력해주세요");
        }
        return money / LOTTO_PRICE_UNIT;
    }

    public static int validateBonusNumber(String inputBonusNumber) {
        return parseInt(inputBonusNumber);
    }

    public static int validateInputAmount(String inputAmount) {
        return parseInt(inputAmount);
    }

    private static int parseInt(String input) {
        if (input.contains(".")) {
            throw new IllegalArgumentException("소수점 값은 입력할 수 없습니다");
        }

        try {
            int value = Integer.parseInt(input);

            if (value < 0) {
                throw new IllegalArgumentException("양수의 금액을 입력해주세요");
            }

            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정상적인 금액을 입력해주세요");
        }
    }
}
