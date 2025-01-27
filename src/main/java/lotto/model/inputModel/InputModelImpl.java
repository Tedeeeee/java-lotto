package lotto.model.inputModel;

import camp.nextstep.edu.missionutils.Console;

public class InputModelImpl implements InputModel {

    @Override
    public String read() {
        return Console.readLine();
    }
}
