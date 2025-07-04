package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.repositorio.SplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SplitService {
    private final SplitRepository splitRepository;

    @Autowired
    public SplitService(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public List<Split> findAll() {
        return splitRepository.findAll();
    }

    public Optional<Split> findById(Integer id) {
        return splitRepository.findById(id);
    }

    public Split save(Split split) {
        return splitRepository.save(split);
    }

    public void deleteById(Integer id) {
        splitRepository.deleteById(id);
    }

    public void updateSplit(Integer id, String titulo) {
        Split split = splitRepository.findById(id).orElseThrow(() -> new RuntimeException("Split no encontrado"));

        split.setTitulo(titulo);

        splitRepository.save(split);
    }
}

