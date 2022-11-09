package com.naponline.lojinha.services;

import com.naponline.lojinha.controllers.dto.ProdutoDTO;
import com.naponline.lojinha.model.Categoria;
import com.naponline.lojinha.model.Produto;
import com.naponline.lojinha.enums.CategoriaStatus;
import com.naponline.lojinha.exceptions.CategoriaInativaException;
import com.naponline.lojinha.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public Page<ProdutoDTO> consultar(Pageable paginacao){
        Page<Produto> produtos = produtoRepository.findAll(paginacao);
        return ProdutoDTO.converter(produtos);
    }

    private Produto consultarProdutoPorId(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        Produto prod = obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        return prod;
    }

    public ProdutoDTO consultarProdutoDTOPorId(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        Produto produto = null; // obj.orElseThrow(()-> new EntityNotFoundException("Produto não encontrado"));
        try {
            produto = obj.get();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("Produto não encontrado");
        }
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        Categoria cat = categoriaService.consultarPorId(produtoDTO.getCategoria().getId());

        if(cat.getStatus() == CategoriaStatus.INATIVA){
            throw new CategoriaInativaException("Categoria inativa!");
        }
        Produto entidadeProduto = new Produto();
        entidadeProduto.setDescricao(produtoDTO.getDescricao());
        entidadeProduto.setPreco(produtoDTO.getPreco());
        entidadeProduto.setEstoque(produtoDTO.getEstoque());
        entidadeProduto.setCategoria(produtoDTO.getCategoria());

        produtoRepository.save(entidadeProduto);
        return new ProdutoDTO(entidadeProduto);
    }

    public ProdutoDTO alterar(Long id, Produto produto){
        Produto prod = this.consultarProdutoPorId(id);

        prod.setDescricao(produto.getDescricao());
        prod.setPreco(produto.getPreco());
        prod.setEstoque(produto.getEstoque());

        ProdutoDTO produtoDTO = new ProdutoDTO(produto);

        return this.salvar(produtoDTO);
    }

    @Transactional
    public void excluir(Long id){
        Produto prod = this.consultarProdutoPorId(id);
        produtoRepository.delete(prod);
    }
}