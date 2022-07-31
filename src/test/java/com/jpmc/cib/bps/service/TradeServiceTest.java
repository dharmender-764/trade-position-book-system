package com.jpmc.cib.bps.service;

import com.jpmc.cib.bps.dto.EventType;
import com.jpmc.cib.bps.dto.TradeEventDto;
import com.jpmc.cib.bps.entity.TradeEvent;
import com.jpmc.cib.bps.repository.TradeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @Test
    public void testShouldSaveTradeEvent() {
        TradeEventDto tradeEventDto = new TradeEventDto();
        tradeEventDto.setEventId(1l);
        tradeEventDto.setAccountNumber("ACC1");
        tradeEventDto.setSecurityName("SEC1");
        tradeEventDto.setEventType(EventType.BUY);
        tradeEventDto.setQuantity(100);

        TradeEvent tradeEvent = new TradeEvent(tradeEventDto);

        Mockito.when(tradeRepository.save(tradeEvent)).thenReturn(tradeEvent);
        TradeEventDto expectedTradeEventDto = tradeService.saveTradeEvent(tradeEventDto);

        Assertions.assertEquals(tradeEventDto, expectedTradeEventDto);
        Mockito.verify(tradeRepository).save(tradeEvent);
    }

    @Test
    public void testShouldReturnTradeEvents() {
        String accountNumber = "ACC1";
        String securityName = "SEC1";
        TradeEvent tradeEvent1 = new TradeEvent();
        tradeEvent1.setEventId(1l);
        tradeEvent1.setAccountNumber(accountNumber);
        tradeEvent1.setSecurityName(securityName);
        tradeEvent1.setEventType(EventType.BUY);
        tradeEvent1.setQuantity(100);

        TradeEvent tradeEvent2 = new TradeEvent();
        tradeEvent1.setEventId(2l);
        tradeEvent1.setAccountNumber(accountNumber);
        tradeEvent1.setSecurityName(securityName);
        tradeEvent1.setEventType(EventType.SELL);
        tradeEvent1.setQuantity(50);

        List<TradeEvent> tradeEvents = new ArrayList<>();
        tradeEvents.add(tradeEvent1);
        tradeEvents.add(tradeEvent2);

        List<TradeEventDto> expectedTradeEventsDtos = new ArrayList<>();
        expectedTradeEventsDtos.add(new TradeEventDto(tradeEvent1));
        expectedTradeEventsDtos.add(new TradeEventDto(tradeEvent2));

        Mockito.when(tradeRepository.findAllByAccountNumberAndSecurityName(accountNumber, securityName)).thenReturn(tradeEvents);
        List<TradeEventDto> actualTradeEventDtos = tradeService.getTradeEvents(accountNumber, securityName);

        Assertions.assertEquals(expectedTradeEventsDtos, actualTradeEventDtos);
        Mockito.verify(tradeRepository).findAllByAccountNumberAndSecurityName(accountNumber, securityName);
    }
}