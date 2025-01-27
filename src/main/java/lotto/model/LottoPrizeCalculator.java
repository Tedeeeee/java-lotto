package lotto.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class LottoPrizeCalculator {
    private final int amount;

    public LottoPrizeCalculator(String amount) {
        this.amount = LottoMoneyValidator.validateInputAmount(amount);
    }

    public double calculatePrize(Map<String, Integer> correctLottoNumberMap) {
        long resultSum = 0L;
        for (Map.Entry<String, Integer> lottoResult : correctLottoNumberMap.entrySet()) {
            String ranking = lottoResult.getKey();
            for (int i = 0; i < lottoResult.getValue(); i++) {
                resultSum += matchingPrice(ranking);
            }
        }

        double resultRate = (double) resultSum / amount * 100;

        return BigDecimal.valueOf(resultRate)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private int matchingPrice(String ranking) {
        return switch (ranking) {
            case "3개 일치 (5,000원)" -> 5000;
            case "4개 일치 (50,000원)" -> 50000;
            case "5개 일치 (1,500,000원)" -> 1500000;
            case "5개 일치, 보너스 볼 일치 (30,000,000원)" -> 30000000;
            case "6개 일치 (2,000,000,000원)" -> 2000000000;
            default -> 0;
        };
    }
}
