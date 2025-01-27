package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Pago;
import com.splitup.crud.repositorio.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    public List<Pago> findBySplitId (Integer splitId) {
        return pagoRepository.findBySplitId(splitId);
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void deleteById(Integer id) {
        pagoRepository.deleteById(id);
    }
}

