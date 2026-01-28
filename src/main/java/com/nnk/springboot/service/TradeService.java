package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeRepository repo;

    public TradeService(TradeRepository repo) {
        this.repo = repo;
    }

    public List<Trade> findAll() {
        return repo.findAll();
    }

    public Trade findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trade not found: " + id));
    }

    public Trade create(Trade trade) {
        return repo.save(trade);
    }

    public Trade update(int id, Trade trade) {
        Trade existing = findById(id);
        trade.setTradeId(existing.getTradeId()); // conserver l'id DB
        return repo.save(trade);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
