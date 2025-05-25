package com.mottu.challenge.controller;


import com.mottu.challenge.dto.PatioDTO;
import com.mottu.challenge.model.Patio;
import com.mottu.challenge.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @GetMapping
    public Page<PatioDTO> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patioService.findAll(pageable)
            .map(patio -> new PatioDTO(patio.getIdPatio(), patio.getLocalizacao(), patio.getCapacidade()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioDTO> getById(@PathVariable Long id) {
        Optional<Patio> patio = patioService.findById(id);
        return patio.map(p -> ResponseEntity.ok(new PatioDTO(p.getIdPatio(), p.getLocalizacao(), p.getCapacidade())))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatioDTO> create(@Valid @RequestBody PatioDTO patioDTO) {
        Patio patio = new Patio();
        patio.setLocalizacao(patioDTO.getLocalizacao());
        patio.setCapacidade(patioDTO.getCapacidade());
        Patio created = patioService.save(patio);
        return ResponseEntity.status(201).body(new PatioDTO(created.getIdPatio(), created.getLocalizacao(), created.getCapacidade()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioDTO> update(@PathVariable Long id, @Valid @RequestBody PatioDTO patioDTO) {
        Optional<Patio> existing = patioService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Patio patio = existing.get();
        patio.setLocalizacao(patioDTO.getLocalizacao());
        patio.setCapacidade(patioDTO.getCapacidade());
        Patio updated = patioService.save(patio);
        return ResponseEntity.ok(new PatioDTO(updated.getIdPatio(), updated.getLocalizacao(), updated.getCapacidade()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Patio> existing = patioService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        patioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
