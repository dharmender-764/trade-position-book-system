package com.jpmc.cib.bps.entity;

import com.jpmc.cib.bps.dto.EventType;
import lombok.Data;

import java.io.Serializable;

@Data
public class TradeEventId implements Serializable {

    private Long eventId;
    private EventType eventType;
}
