package com.naponline.lojinha.controllers.dto;

import com.naponline.lojinha.model.Categoria;
import com.naponline.lojinha.model.Produto;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProdutoDTO {

    private long id;

    @NotBlank(message = "Informe descrição")
    @Size(message = "O campo deve ter entre 3 e 20 caracteres", max = 20, min = 3)
    private String descricao;

    private Float preco;

    private Integer estoque;

    private Categoria categoria;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto){
        id = produto.getId();
        descricao = produto.getDescricao();
        preco = produto.getPreco();
        estoque = produto.getEstoque();
        categoria = produto.getCategoria();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public static Page<ProdutoDTO> converter(Page<Produto> produtos) {
        return produtos.map(ProdutoDTO::new);
    }
}