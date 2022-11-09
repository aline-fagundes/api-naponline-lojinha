package com.naponline.lojinha.services;

import com.naponline.lojinha.model.Categoria;
import com.naponline.lojinha.model.Produto;
import com.naponline.lojinha.enums.CategoriaStatus;
import com.naponline.lojinha.repositories.CategoriaRepository;
import com.naponline.lojinha.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Categoria> consultar(){
        return categoriaRepository.findAll();
    }

    public Categoria consultarPorId(Long id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        Categoria cat = null; // obj.orElseThrow(()-> new EntityNotFoundException("Categoria não encontrado"));
        try {
            cat = obj.get();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("Categoria não encontrado");
        }
        return cat;
    }

    public List<Categoria> consultarPorStatus(String status) {
        if(status.equalsIgnoreCase("ATIVA")) {
            return categoriaRepository.findCategoriasByStatus(CategoriaStatus.ATIVA);
        }
        if(status.equalsIgnoreCase("INATIVA")) {
            return categoriaRepository.findCategoriasByStatus(CategoriaStatus.INATIVA);
        }
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria salvar(Categoria categoria){
        //validações
        return categoriaRepository.save(categoria);
    }

    public Categoria alterar(Long id, Categoria categoria){
        Categoria cat = this.consultarPorId(id);

        cat.setDescricao(categoria.getDescricao());
        cat.setStatus(categoria.getStatus());

        return this.salvar(cat);
    }

    @Transactional
    public void excluir(Long id){
        Categoria cat = this.consultarPorId(id);

        List<Produto> lista = produtoRepository.findByCategoria(cat);
        if(lista.size() > 0){
            cat.setStatus(CategoriaStatus.INATIVA);
            categoriaRepository.save(cat);
        } else {
            categoriaRepository.delete(cat);
        }
    }
}