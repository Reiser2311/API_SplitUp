package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findBySplitId (Integer splitId);
}

