package com.splitup.crud.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.splitup.crud.conversor.StringArrayConverter;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Split")
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;

    @Column(columnDefinition = "text[]")
    @Convert(converter = StringArrayConverter.class)
    private String[] participantes;

    @ManyToOne
    @JoinColumn(name = "usuario_email", referencedColumnName = "email", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "split", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<Pago> pagos;

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

    public String[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String[] texto) {
        this.participantes = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
