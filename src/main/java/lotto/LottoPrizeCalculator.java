package lotto;

public class LottoPrizeCalculator {
    private final int amount;

    public LottoPrizeCalculator(String amount) {
        this.amount = LottoMoneyValidator.validateInputAmount(amount);
    }
}
