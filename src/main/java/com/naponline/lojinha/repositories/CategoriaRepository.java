package com.naponline.lojinha.repositories;

import com.naponline.lojinha.model.Categoria;
import com.naponline.lojinha.enums.CategoriaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c where c.status = ?1")
    List<Categoria> findCategoriasByStatus(CategoriaStatus status);

   /* @Query(value = "select * from tb_categorias where tb_categorias = ?1", nativeQuery = true);
    List<Categoria> findCategorias(CategoriaStatus status);*/
}
