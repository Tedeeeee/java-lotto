package lotto;

import java.util.List;

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
}
