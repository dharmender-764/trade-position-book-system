package com.jpmc.cib.bps.entity;

import com.jpmc.cib.bps.dto.EventType;
import lombok.Data;

@Data
public class TradeEventId {

    private Long eventId;
    private EventType eventType;
}
