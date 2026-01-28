package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    private final RuleNameRepository repo;

    public RuleNameService(RuleNameRepository repo) {
        this.repo = repo;
    }

    public List<RuleName> findAll() {
        return repo.findAll();
    }

    public RuleName findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RuleName not found: " + id));
    }

    public RuleName create(RuleName ruleName) {
        return repo.save(ruleName);
    }

    public RuleName update(int id, RuleName ruleName) {
        RuleName existing = findById(id);
        ruleName.setId(existing.getId()); // conserver l'id
        return repo.save(ruleName);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
