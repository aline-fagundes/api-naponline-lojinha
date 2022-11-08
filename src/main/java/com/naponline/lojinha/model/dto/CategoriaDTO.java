package com.naponline.lojinha.model.dto;

import com.naponline.lojinha.enums.CategoriaStatus;
import com.naponline.lojinha.model.entity.Categoria;
import com.naponline.lojinha.model.entity.Produto;

import javax.persistence.*;
import java.time.Instant;

public class CategoriaDTO {

    private long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaStatus status = CategoriaStatus.ATIVA;

    public CategoriaDTO(){
    }

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        descricao = categoria.getDescricao();
        status = categoria.getStatus();
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
}
