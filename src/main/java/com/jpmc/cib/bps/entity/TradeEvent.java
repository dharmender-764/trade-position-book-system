package com.jpmc.cib.bps.entity;

import com.jpmc.cib.bps.dto.EventType;
import com.jpmc.cib.bps.dto.TradeEventDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="trade_events")
@IdClass(TradeEventId.class)
public class TradeEvent {

    @Id
    private Long eventId;
    private String accountNumber;
    private String securityName;
    @Id
    private EventType eventType;
    private int quantity;

    public TradeEvent() {

    }

    public TradeEvent(TradeEventDto tradeEvent) {
        this.eventId = tradeEvent.getEventId();
        this.accountNumber = tradeEvent.getAccountNumber();
        this.securityName = tradeEvent.getSecurityName();
        this.eventType = tradeEvent.getEventType();
        this.quantity = tradeEvent.getQuantity();
    }
}
