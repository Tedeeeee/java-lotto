package lotto.controller;

import lotto.model.LottoMatcher;
import lotto.model.LottoMoneyValidator;
import lotto.model.LottoPaper;
import lotto.model.LottoPrizeCalculator;
import lotto.model.inputModel.InputModel;
import lotto.model.inputModel.InputModelImpl;
import lotto.model.randomNumber.LottoNumberGenerator;
import lotto.model.randomNumber.NextStepLottoNumberGenerator;
import lotto.view.LottoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final LottoView lottoView;
    private final LottoNumberGenerator lottoNumberGenerator;
    private final InputModel inputModel;

    public LottoController(LottoView lottoView) {
        this.lottoView = lottoView;
        this.lottoNumberGenerator = new NextStepLottoNumberGenerator();
        this.inputModel = new InputModelImpl();
    }

    public void lottoDrawing() {
        try {
            String inputAmount = processPurchaseAmount();
            LottoPaper lottoPaper = initializeLottoPaper(inputAmount);
            LottoMatcher lottoMatcher = initializeLottoMatcher();
            Map<String, Integer> lottoMatchResult = displayMatchResults(lottoMatcher, lottoPaper);
            displayYieldResult(inputAmount, lottoMatchResult);
        } catch (IllegalArgumentException e) {
            lottoView.errorMessage(e.getMessage());
        }
    }

    private LottoPaper initializeLottoPaper(String inputAmount) {
        LottoPaper lottoPaper = LottoPaper.createWithCount(inputAmount, lottoNumberGenerator);
        notifyLottoPurchase(lottoPaper);
        return lottoPaper;
    }

    private LottoMatcher initializeLottoMatcher() {
        LottoMatcher lottoMatcher = requestWinningLottoNumbers();
        requestBonusNumber(lottoMatcher);
        return lottoMatcher;
    }

    // 금액 입력 받기
    private String processPurchaseAmount() {
        lottoView.requestPurchaseAmount();
        String inputAmount = inputModel.read();
        LottoMoneyValidator.validateInputAmount(inputAmount);
        lottoView.nextLine();

        return inputAmount;
    }

    // 구입한 로또 알림
    private void notifyLottoPurchase(LottoPaper lottoPaper) {
        lottoView.purchaseLottoCount(lottoPaper);
        lottoView.showLottoNumberList(lottoPaper);
        lottoView.nextLine();
    }

    // 당첨 번호 입력
    private LottoMatcher requestWinningLottoNumbers() {
        lottoView.inputAnswerLottoNumbers();
        String answerLottoNumbers = inputModel.read();
        List<Integer> answerLottoNumberList = new ArrayList<>();
        answerLottoNumberList = LottoMoneyValidator.splitInputAnswerLottoNumber(answerLottoNumbers);
        LottoMatcher lottoMatcher = LottoMatcher.from(answerLottoNumberList);
        lottoView.nextLine();
        return lottoMatcher;
    }

    // 보너스 번호 입력
    private void requestBonusNumber(LottoMatcher lottoMatcher) {
        lottoView.inputBonusNumber();
        String bonusLottoNumbers = inputModel.read();
        lottoMatcher.insertBonusNumber(bonusLottoNumbers);
        lottoView.nextLine();
    }

    // 당첨 결과
    private Map<String, Integer> displayMatchResults(LottoMatcher lottoMatcher, LottoPaper lottoPaper) {
        Map<String, Integer> lottoMatchResult = lottoMatcher.countMatchingNumbers(lottoPaper);
        lottoView.lottoMatchResultView(lottoMatchResult);
        return lottoMatchResult;
    }

    // 수익률 공개
    private void displayYieldResult(String inputAmount, Map<String, Integer> lottoMatchResult) {
        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator(inputAmount);
        double yieldResult = lottoPrizeCalculator.calculatePrize(lottoMatchResult);
        lottoView.yieldResult(yieldResult);
    }
}
