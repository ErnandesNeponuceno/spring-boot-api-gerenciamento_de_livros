package com.sistema.sgb.controller;

import com.sistema.sgb.dto.CategoriaDTO;

import com.sistema.sgb.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDTO>> listarTodos() {
        List<CategoriaDTO> categorias = service.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> listarPorId(@PathVariable Long id) {
        CategoriaDTO categoria = service.listarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> incluir(@RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = service.incluir(dto);
        return ResponseEntity.status(201).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoriaAtualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
