package com.jpmc.cib.bps.repository;

import com.jpmc.cib.bps.entity.TradeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeEvent, Long> {

    List<TradeEvent> findAllByAccountNumberAndSecurityName(String accountNumber, String securityName);
}
