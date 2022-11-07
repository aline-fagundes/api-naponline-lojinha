package com.naponline.lojinha.entidades;

import com.naponline.lojinha.enums.CategoriaStatus;

import javax.persistence.*;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaStatus status = CategoriaStatus.ATIVA;

//    @OneToMany(mappedBy = "categoria")
//    private List<Produto> produtos = new ArrayList<>();

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

//    public List<Produto> getProdutos() {
//        return produtos;
//    }

//    public void setProdutos(List<Produto> produtos) {
//        this.produtos = produtos;
//    }
}
