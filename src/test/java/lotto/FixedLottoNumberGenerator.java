package lotto;

import lotto.randomNumber.LottoNumberGenerator;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return List.of(1, 2, 3, 4, 5, 6);
    }
}
