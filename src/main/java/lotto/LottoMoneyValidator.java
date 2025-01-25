package lotto;

public class LottoMoneyValidator {

    public static int validateThousandUnit(String inputMoney) {

        int money = Integer.parseInt(inputMoney);

        if (money < 0) {
            throw new IllegalArgumentException("양수의 금액을 입력해주세요");
        }

        if (money % 1000 > 0) {
            throw new IllegalArgumentException("천원 단위의 금액을 입력해주세요");
        }

        return money / 1000;
    }


}
