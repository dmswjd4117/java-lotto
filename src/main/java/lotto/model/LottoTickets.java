package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LottoTickets {

    private final List<LottoTicket> lottoTicketList;

    public LottoTickets(List<LottoTicket> lottoTicketList) {
        this.lottoTicketList = Collections.unmodifiableList(lottoTicketList);
    }

    public Map<Rank, Long> getRankMap(LottoTicket winningNumbers){
        Map<Rank, Long> res = lottoTicketList.stream()
                .map(ticket -> ticket.getRank(winningNumbers))
                .filter(Rank::isWin)
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        return res;
    }


}
