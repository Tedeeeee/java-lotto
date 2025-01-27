package lotto.model;

import java.util.Arrays;
import java.util.List;

public class LottoInputValidator {
    private static final int LOTTO_PRICE_UNIT = 1000;
    private static final String SEPARATOR = ",";

    private static final String ERROR_MINIMUM_PURCHASE = "로또를 한 개 이상 구매해주세요";
    private static final String ERROR_UNIT_AMOUNT = "천원 단위의 금액을 입력해주세요";
    private static final String ERROR_DECIMAL_NOT_ALLOWED = "소수점 값은 입력할 수 없습니다";
    private static final String ERROR_INVALID_AMOUNT = "정상적인 금액을 입력해주세요";
    private static final String ERROR_NEGATIVE_AMOUNT = "양수의 금액을 입력해주세요";

    // 테스트 작성 필요
    public static List<Integer> splitInputAnswerLottoNumber(String inputNumbers) {
        return Arrays.stream(inputNumbers.split(SEPARATOR))
                .map(Integer::parseInt)
                .toList();
    }

    public static int calculateLottoCount(String inputMoney) {
        int money = parseInt(inputMoney);
        if ((money / LOTTO_PRICE_UNIT) <= 0) {
            throw new IllegalArgumentException(ERROR_MINIMUM_PURCHASE);
        }

        if ((money % LOTTO_PRICE_UNIT) > 0) {
            throw new IllegalArgumentException(ERROR_UNIT_AMOUNT);
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
            throw new IllegalArgumentException(ERROR_DECIMAL_NOT_ALLOWED);
        }

        int value = 0;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_AMOUNT);
        }

        if (value < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_AMOUNT);
        }

        return value;
    }
}
