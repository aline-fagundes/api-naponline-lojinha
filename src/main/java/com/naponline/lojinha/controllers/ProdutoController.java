package com.naponline.lojinha.controllers;

import com.naponline.lojinha.model.dto.ProdutoDTO;
import com.naponline.lojinha.model.entity.Produto;
import com.naponline.lojinha.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos") //http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> consultarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> consultarPorId(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(service.consultarPorIdProdutoDTO(id));
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