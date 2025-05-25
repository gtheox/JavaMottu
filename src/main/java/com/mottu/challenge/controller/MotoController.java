package com.mottu.challenge.controller;

import com.mottu.challenge.model.Patio;
import com.mottu.challenge.dto.MotoDTO;
import com.mottu.challenge.model.Moto;
import com.mottu.challenge.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public Page<MotoDTO> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return motoService.findAll(pageable)
            .map(moto -> new MotoDTO(moto.getIdMoto(), moto.getModelo(), moto.getPlaca(), moto.getPatio().getIdPatio()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> getById(@PathVariable Long id) {
        Optional<Moto> moto = motoService.findById(id);
        return moto.map(m -> ResponseEntity.ok(new MotoDTO(m.getIdMoto(), m.getModelo(), m.getPlaca(), m.getPatio().getIdPatio())))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MotoDTO> create(@Valid @RequestBody MotoDTO motoDTO) {
        Moto moto = new Moto();
        moto.setModelo(motoDTO.getModelo());
        moto.setPlaca(motoDTO.getPlaca());
        // Aqui você pode usar o ID do pátio para associar a moto
        Patio patio = new Patio();
        patio.setIdPatio(motoDTO.getIdPatio()); // Só se necessário
        moto.setPatio(patio);
        Moto created = motoService.save(moto);
        return ResponseEntity.status(201).body(new MotoDTO(created.getIdMoto(), created.getModelo(), created.getPlaca(), created.getPatio().getIdPatio()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> update(@PathVariable Long id, @Valid @RequestBody MotoDTO motoDTO) {
        Optional<Moto> existing = motoService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Moto moto = existing.get();
        moto.setModelo(motoDTO.getModelo());
        moto.setPlaca(motoDTO.getPlaca());
        // Atualizar o pátio se necessário
        Patio patio = new Patio();
        patio.setIdPatio(motoDTO.getIdPatio());
        moto.setPatio(patio);
        Moto updated = motoService.save(moto);
        return ResponseEntity.ok(new MotoDTO(updated.getIdMoto(), updated.getModelo(), updated.getPlaca(), updated.getPatio().getIdPatio()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Moto> existing = motoService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        motoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
