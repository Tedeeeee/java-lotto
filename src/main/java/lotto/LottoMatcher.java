package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatcher {
    private final Lotto answerLotto;
    private Number bonusNumber;

    public LottoMatcher(Lotto answerLotto) {
        this.answerLotto = answerLotto;
    }

    public static LottoMatcher from(List<Integer> answerNumber) {
        Lotto answerLotto = new Lotto(answerNumber);
        return new LottoMatcher(answerLotto);
    }

    public void insertBonusNumber(String inputBonusNumber) {
        int bonusNumber = LottoMoneyValidator.validateBonusNumber(inputBonusNumber);
        this.bonusNumber = new Number(bonusNumber);
    }

    public Map<String, Integer> countMatchingNumbers(LottoPaper lottoPaper) {
        List<Integer> integers = lottoPaper.compareAnswerLottoNumbers(answerLotto, bonusNumber);

        Map<String, Integer> matchingNumbers = initializeWinningMap();

        for (Integer integer : integers) {
            correctNumberMapping(matchingNumbers, integer);
        }

        return matchingNumbers;
    }

    private Map<String, Integer> initializeWinningMap() {
        Map<String, Integer> winningMap = new HashMap<>();
        winningMap.put("3개 일치 (5,000원)", 0);
        winningMap.put("4개 일치 (50,000원)", 0);
        winningMap.put("5개 일치 (1,500,000원)", 0);
        winningMap.put("5개 일치, 보너스 볼 일치 (30,000,000원)", 0);
        winningMap.put("6개 일치 (2,000,000,000원)", 0);
        return winningMap;
    }

    private void correctNumberMapping(Map<String, Integer> matchingNumbers, int currentNumber) {
        switch (currentNumber) {
            case 3:
                matchingNumbers.put("3개 일치 (5,000원)", matchingNumbers.getOrDefault("3개 일치 (5,000원)", 0) + 1);
                break;
            case 4:
                matchingNumbers.put("4개 일치 (50,000원)", matchingNumbers.getOrDefault("4개 일치 (50,000원)", 0) + 1);
                break;
            case 5:
                matchingNumbers.put("5개 일치 (1,500,000원)", matchingNumbers.getOrDefault("5개 일치 (1,500,000원)", 0) + 1);
                break;
            case 6:
                matchingNumbers.put("6개 일치 (2,000,000,000원)", matchingNumbers.getOrDefault("6개 일치 (2,000,000,000원)", 0) + 1);
                break;
            case 7:
                matchingNumbers.put("5개 일치, 보너스 볼 일치 (30,000,000원)", matchingNumbers.getOrDefault("5개 일치, 보너스 볼 일치 (30,000,000원)", 0) + 1);
                break;
            default:
                break;
        }
    }
}
