package com.jpmc.cib.bps.controller;

import com.jpmc.cib.bps.dto.TradeEventDto;
import com.jpmc.cib.bps.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class TradeController {

    private TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/tradeEvents")
    public List<TradeEventDto> getTradeEvents(@RequestParam String accountNumber, @RequestParam String securityName) {
        return tradeService.getTradeEvents(accountNumber, securityName);
    }

    @PostMapping("/tradeEvent")
    public TradeEventDto saveTradeEvent(@Valid @RequestBody TradeEventDto tradeEventDto) {
        return tradeService.saveTradeEvent(tradeEventDto);
    }
}
