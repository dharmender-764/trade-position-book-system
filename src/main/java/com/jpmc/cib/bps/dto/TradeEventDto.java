package com.jpmc.cib.bps.dto;

import com.jpmc.cib.bps.entity.TradeEvent;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TradeEventDto {

    @NotNull
    @Min(value = 1, message = "Trade Id must be greater than 0")
    private Long eventId;

    @NotEmpty
    private String accountNumber;

    @NotEmpty
    private String securityName;

    @NotNull
    private EventType eventType;

    @NotNull
    @Min(value = 1, message = "Trade quantity must be greater than 0")
    private Integer quantity;

    public TradeEventDto(){

    }

    public TradeEventDto(TradeEvent tradeEvent) {
        this.eventId = tradeEvent.getEventId();
        this.accountNumber = tradeEvent.getAccountNumber();
        this.securityName = tradeEvent.getSecurityName();
        this.eventType = tradeEvent.getEventType();
        this.quantity = tradeEvent.getQuantity();
    }
}
