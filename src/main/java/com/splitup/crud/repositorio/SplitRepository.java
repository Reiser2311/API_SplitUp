package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Split;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SplitRepository extends JpaRepository<Split, Integer> {
}

