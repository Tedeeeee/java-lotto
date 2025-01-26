package lotto;

public class LottoMoneyValidator {
    private static final int LOTTO_PRICE_UNIT = 1000;

    public static int validateThousandUnit(String inputMoney) {

        if (inputMoney.contains(".")) {
            throw new IllegalArgumentException("소수점 값은 입력할 수 없습니다");
        }

        try {
            int money = Integer.parseInt(inputMoney);

            if (money < 0) {
                throw new IllegalArgumentException("양수의 금액을 입력해주세요");
            }

            if ((money / 1000) <= 0) {
                throw new IllegalArgumentException("로또를 한 개 이상 구매해주세요");
            }

            if ((money % 1000) > 0) {
                throw new IllegalArgumentException("천원 단위의 금액을 입력해주세요");
            }

            return money / 1000;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정상적인 금액을 입력해주세요");
        }
    }


}
