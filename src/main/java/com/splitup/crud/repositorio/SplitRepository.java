package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Split;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Integer> {
}

