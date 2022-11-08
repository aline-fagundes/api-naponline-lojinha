package com.naponline.lojinha.repositories;

import com.naponline.lojinha.model.entity.Categoria;
import com.naponline.lojinha.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(Categoria cat);
}
