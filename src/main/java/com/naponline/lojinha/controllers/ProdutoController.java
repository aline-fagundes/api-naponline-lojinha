package com.naponline.lojinha.controllers;

import com.naponline.lojinha.entidades.Produto;
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
    public ResponseEntity<List<Produto>> consultarProdutos(){
        List<Produto> lista = service.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> consultarPorId(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(service.consultarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
        Produto prod = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable("id") Long id, @Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
        //return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}