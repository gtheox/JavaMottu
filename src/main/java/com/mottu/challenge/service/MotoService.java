package com.mottu.challenge.service;

import com.mottu.challenge.model.Moto;
import com.mottu.challenge.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Cacheable(value = "motosCache", key = "#page")
    public Page<Moto> findAll(Pageable pageable) {
        return motoRepository.findAll(pageable);
    }

    public Optional<Moto> findById(Long id) {
        return motoRepository.findById(id);
    }

    public Moto save(Moto moto) {
        return motoRepository.save(moto);
    }

    public void deleteById(Long id) {
        motoRepository.deleteById(id);
    }
}
