package com.splitup.crud.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private double importe;
    private int pagadoPor;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "split_id", nullable = false)
    private Split split;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getPagadoPor() {
        return pagadoPor;
    }

    public void setPagadoPor(int pagadoPor) {
        this.pagadoPor = pagadoPor;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
