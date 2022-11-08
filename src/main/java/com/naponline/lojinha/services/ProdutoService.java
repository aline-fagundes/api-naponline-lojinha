package com.naponline.lojinha.services;

import com.naponline.lojinha.model.entity.Categoria;
import com.naponline.lojinha.model.entity.Produto;
import com.naponline.lojinha.enums.CategoriaStatus;
import com.naponline.lojinha.exceptions.CategoriaInativaException;
import com.naponline.lojinha.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public List<Produto> consultar(){
        return produtoRepository.findAll();
    }

    public Produto consultarPorId(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        Produto prod = null; // obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        try {
            prod = obj.get();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("Produto não encontrado");
        }
        return prod;
    }

    @Transactional
    public Produto salvar(Produto produto){
        Categoria cat = categoriaService.consultarPorId(produto.getCategoria().getId());

        if(cat.getStatus() == CategoriaStatus.INATIVA){
            throw new CategoriaInativaException("Categoria inativa!");
        }
        return produtoRepository.save(produto);
    }

    public Produto alterar(Long id, Produto produto){
        Produto prod = this.consultarPorId(id);

        prod.setDescricao(produto.getDescricao());
        prod.setPreco(produto.getPreco());
        prod.setEstoque(produto.getEstoque());

        return this.salvar(prod);
    }

    @Transactional
    public void excluir(Long id){
        Produto prod = this.consultarPorId(id);
        produtoRepository.delete(prod);
    }
}