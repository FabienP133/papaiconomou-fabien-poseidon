package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository repo;

    public RatingService(RatingRepository repo) {
        this.repo = repo;
    }

    public List<Rating> findAll() {
        return repo.findAll();
    }

    public Rating findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found: " + id));
    }

    public Rating create(Rating rating) {
        return repo.save(rating);
    }

    public Rating update(int id, Rating rating) {
        Rating existing = findById(id);
        rating.setId(existing.getId()); // conserver l'id
        return repo.save(rating);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
