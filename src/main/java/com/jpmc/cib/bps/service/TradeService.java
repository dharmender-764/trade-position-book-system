package com.jpmc.cib.bps.service;

import com.jpmc.cib.bps.dto.TradeEventDto;
import com.jpmc.cib.bps.entity.TradeEvent;
import com.jpmc.cib.bps.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TradeService {

    private TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public TradeEventDto saveTradeEvent(TradeEventDto tradeEventDto) {
        TradeEvent tradeEvent = new TradeEvent(tradeEventDto);
        tradeEvent = tradeRepository.save(tradeEvent);

        return new TradeEventDto(tradeEvent);
    }

    public List<TradeEventDto> getTradeEvents(String accountNumber, String securityName) {
        List<TradeEvent> tradeEvents = tradeRepository.findAllByAccountNumberAndSecurityName(accountNumber, securityName);

        return tradeEvents.stream().
                map(tradeEvent -> new TradeEventDto(tradeEvent)).
                collect(Collectors.toList());
    }
}
