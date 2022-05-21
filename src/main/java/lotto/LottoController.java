package lotto;

import lotto.model.*;
import lotto.view.Input;
import lotto.view.Output;

import java.util.List;

public class LottoController {

    public void start(){
        Money money = new Money(Input.readMoney());

        // 로또 티켓들 생성
        LottoTickets lottoTickets = getLottoTickets(money);
        Output.printLottoTicketList(lottoTickets);

        // 지난 주 당첨 번호와 보너스볼
        LottoTicket winningTicket =  Input.readPreWeekWinningLottoNums();
        LottoNumber bonusBall = Input.readLottoNumber();

        LottoResult lottoResult = lottoTickets.getLottoResult(winningTicket, bonusBall);
        Output.printWinningStatics(money, lottoResult);
    }

    private LottoTickets getLottoTickets(Money money){
        int ticketCnt = money.calculateTicketCnt();
        Output.printLottoCnt(ticketCnt);

        int manualLottoCnt = Input.readManualLottoCnt();
        List<LottoTicket> manualLottoTickets = Input.readManualLottoNumbers(manualLottoCnt);

        return LottoGame.generateLottoTickets(manualLottoTickets, money);
    }
}
