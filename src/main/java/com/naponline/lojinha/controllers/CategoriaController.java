package com.naponline.lojinha.controllers;

import com.naponline.lojinha.model.Categoria;
import com.naponline.lojinha.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias") //http://localhost:8080/categorias
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping("/todas")
    public ResponseEntity<List<Categoria>> consultarCategorias(){
        List<Categoria> lista = service.consultar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> consultarPorStatus(@RequestParam(value = "status", defaultValue = "") String status){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> consultarPorId(@PathVariable("id") Long id){
        return  ResponseEntity.ok().body(service.consultarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
        Categoria cat = service.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable("id") Long id, @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
        //return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}