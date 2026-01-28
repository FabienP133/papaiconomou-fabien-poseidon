package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository repo;

    public BidListService(BidListRepository repo) {
        this.repo = repo;
    }

    public List<BidList> findAll() {
        return repo.findAll();
    }

    public BidList findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BidList not found: " + id));
    }

    public BidList create(BidList bid) {
        return repo.save(bid);
    }

    public BidList update(int id, BidList bid) {
        BidList existing = findById(id);
        bid.setBidListId(existing.getBidListId()); // important de conserver l'id
        return repo.save(bid);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
