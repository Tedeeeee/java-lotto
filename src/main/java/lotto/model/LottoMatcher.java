package lotto.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        int bonusNumber = LottoInputValidator.validateBonusNumber(inputBonusNumber);
        this.bonusNumber = new Number(bonusNumber);
    }

    public Map<String, Integer> countMatchingNumbers(LottoPaper lottoPaper) {
        List<Integer> correctNumbers = lottoPaper.compareAnswerLottoNumbers(answerLotto, bonusNumber);

        Map<String, Integer> matchingNumbers = initializeWinningMap();

        for (Integer correctCount : correctNumbers) {
            correctNumberMapping(matchingNumbers, correctCount);
        }

        return matchingNumbers;
    }

    private Map<String, Integer> initializeWinningMap() {
        Map<String, Integer> winningMap = new TreeMap<>();
        winningMap.put("3개 일치 (5,000원)", 0);
        winningMap.put("4개 일치 (50,000원)", 0);
        winningMap.put("5개 일치 (1,500,000원)", 0);
        winningMap.put("5개 일치, 보너스 볼 일치 (30,000,000원)", 0);
        winningMap.put("6개 일치 (2,000,000,000원)", 0);
        return winningMap;
    }

    private void correctNumberMapping(Map<String, Integer> matchingNumbers, int correctCount) {
        switch (correctCount) {
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
