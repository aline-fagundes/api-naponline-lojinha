package com.naponline.lojinha.controllers;

import com.naponline.lojinha.controllers.dto.ProdutoDTO;
import com.naponline.lojinha.model.Produto;
import com.naponline.lojinha.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> consultarProdutos(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable paginacao){
        Page<ProdutoDTO> produtos = service.consultar(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> consultarPorId(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(service.consultarProdutoDTOPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produtoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable("id") Long id, @Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}