package com.sistema.sgb.controller;

import com.sistema.sgb.service.LivroService;
import com.sistema.sgb.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<LivroDTO>> listarTodos() {
        List<LivroDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> listarPorId(@PathVariable Long id) {
        LivroDTO livro = service.listarPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> incluir(@RequestBody LivroDTO dto){
        LivroDTO livro = service.incluir(dto);
        return ResponseEntity.status(201).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody LivroDTO dto) {
        LivroDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) throws Exception {
        service.deletar(id);
        return ResponseEntity.noContent().build();

    }

}
