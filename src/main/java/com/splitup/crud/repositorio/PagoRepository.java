package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}

