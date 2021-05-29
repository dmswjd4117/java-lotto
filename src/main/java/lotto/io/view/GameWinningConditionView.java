package lotto.io.view;

import lotto.game.domain.entity.Round;
import lotto.io.domain.code.ProcessCode;
import lotto.io.domain.code.ViewCode;
import lotto.io.domain.entity.ViewStatus;
import lotto.io.domain.vo.InputText;

public class GameWinningConditionView extends View {

	private GameWinningConditionView() {

	}

	@Override
	public ViewCode viewCode() {
		return ViewCode.GAME_WINNING_CONDITION;
	}

	@Override
	public void displayProcess(ViewStatus viewStatus, Round round, InputText inputText) {
		if (viewStatus.isRequestInputProcess()) {
			processWhenRequestInput();
			changeViewStatusWhenRequestInput(viewStatus);
			return;
		}
		if (viewStatus.isSystemInProcess()) {
			processWhenSystemIn(round, inputText);
			changeViewStatusWhenSystemIn(viewStatus);
			return;
		}
		changeViewStatusDefault(viewStatus);
	}

	public static View generate() {
		return new GameWinningConditionView();
	}

	private void changeViewStatusWhenRequestInput(ViewStatus viewStatus) {
		viewStatus.changeCurrentProcessCode(ProcessCode.SYSTEM_IN);
	}

	private void changeViewStatusWhenSystemIn(ViewStatus viewStatus) {
		viewStatus.changeCurrentViewCode(ViewCode.BONUS_BALL);
		viewStatus.changeCurrentProcessCode(ProcessCode.REQUEST_INPUT);
	}

	private void changeViewStatusDefault(ViewStatus viewStatus) {
		viewStatus.changeCurrentProcessCode(ProcessCode.REQUEST_INPUT);
	}

	private void processWhenRequestInput() {
		System.out.println(viewCode().inputRequestMessage());
	}

	private void processWhenSystemIn(Round round, InputText inputText) {
		round.setupGameWinningCondition(inputText);
	}
}