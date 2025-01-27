package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoPaper;

import java.util.List;
import java.util.Map;

public class LottoView {

    private static final String ERROR_PREFIX = "[ERROR]";

    public void errorMessage(String message) {
        System.out.printf(ERROR_PREFIX + message);
    }

    public void nextLine() {
        System.out.println();
    }

    public void requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void purchaseLottoCount(LottoPaper lottoPaper) {
        System.out.printf("%d개를 구매했습니다.%n", lottoPaper.purchaseLottoCount());
    }

    public void showLottoNumberList(LottoPaper lottoPaper) {
        List<Lotto> lottos = lottoPaper.getLottos();

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void inputAnswerLottoNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void lottoMatchResultView(Map<String, Integer> lottoMatchResult) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Map.Entry<String, Integer> result : lottoMatchResult.entrySet()) {
            System.out.println(result.getKey() + " - " + result.getValue() + "개");
        }
    }

    public void yieldResult(double yield) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", yield);
    }
}
