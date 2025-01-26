package lotto;

import java.util.List;

public class LottoMatcher {
    private final Lotto answerLotto;
    private int bonusNumber;

    public LottoMatcher(Lotto answerLotto) {
        this.answerLotto = answerLotto;
    }

    public static LottoMatcher from(List<Integer> answerNumber) {
        Lotto answerLotto = new Lotto(answerNumber);
        return new LottoMatcher(answerLotto);
    }

    public void insertBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
