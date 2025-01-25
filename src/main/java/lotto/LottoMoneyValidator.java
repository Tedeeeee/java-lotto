package lotto;

public class LottoMoneyValidator {

    public static int validateThousandUnit(String inputMoney) {

        int money = 0;
        try {
            money = Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정상적인 금액을 입력해주세요");
        }

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
    }


}
