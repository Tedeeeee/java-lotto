package lotto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Integer> countMatchingNumbers(LottoPaper lottoPaper) {
        return lottoPaper.compareAnswerLottoNumbers(answerLotto, bonusNumber);
    }

    public Map<Integer, Integer> countMatchingNumbersAsMap(List<Integer> correctNumbers) {
        return correctNumbers.stream()
                .filter(correctNumber -> correctNumber < 6)
                .collect(Collectors.groupingBy(
                        correctNumber -> correctNumber,
                        Collectors.summingInt(value -> 1)
                ));
    }
}
