package com.naponline.lojinha.model;

import com.naponline.lojinha.enums.CategoriaStatus;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaStatus status = CategoriaStatus.ATIVA;

    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;

    @Column(columnDefinition = "DATETIME")
    private Instant updatedAt;

    @PrePersist
    public void prePersit(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaStatus getStatus() {
        return status;
    }

    public void setStatus(CategoriaStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
