package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    private final CurvePointRepository repo;

    public CurvePointService(CurvePointRepository repo) {
        this.repo = repo;
    }

    public List<CurvePoint> findAll() {
        return repo.findAll();
    }

    public CurvePoint findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CurvePoint not found: " + id));
    }

    public CurvePoint create(CurvePoint curvePoint) {
        return repo.save(curvePoint);
    }

    public CurvePoint update(int id, CurvePoint curvePoint) {
        CurvePoint existing = findById(id);
        curvePoint.setId(existing.getId()); // conserver l'id
        return repo.save(curvePoint);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
