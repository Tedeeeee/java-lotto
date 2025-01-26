package lotto;

import lotto.randomNumber.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoPaper {
    private List<Lotto> lottos;

    public LottoPaper(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottoPaper createWithCount(String inputMoney, LottoNumberGenerator numberGenerator) {
        int lottoCount = LottoMoneyValidator.calculateLottoCount(inputMoney);
        return new LottoPaper(generateLottoList(lottoCount, numberGenerator));
    }

    private static List<Lotto> generateLottoList(int lottoCount, LottoNumberGenerator numberGenerator) {
        List<Lotto> makeLottoList = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> uniqueNumberList = numberGenerator.generate();
            makeLottoList.add(new Lotto(uniqueNumberList));
        }

        return makeLottoList;
    }

    public List<Integer> compareAnswerLottoNumbers(Lotto answerLotto) {
        return lottos.stream()
                .map(lotto -> lotto.countMatchingNumbers(answerLotto))
                .toList();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
