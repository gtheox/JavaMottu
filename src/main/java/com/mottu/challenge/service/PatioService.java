package com.mottu.challenge.service;

import com.mottu.challenge.model.Patio;
import com.mottu.challenge.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    public Page<Patio> findAll(Pageable pageable) {
        return patioRepository.findAll(pageable);
    }

    public Optional<Patio> findById(Long id) {
        return patioRepository.findById(id);
    }

    public Patio save(Patio patio) {
        return patioRepository.save(patio);
    }

    public void deleteById(Long id) {
        patioRepository.deleteById(id);
    }
}
