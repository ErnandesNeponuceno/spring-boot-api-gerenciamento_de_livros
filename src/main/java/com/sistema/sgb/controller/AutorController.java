package com.sistema.sgb.controller;

import com.sistema.sgb.dto.AutorDTO;
import com.sistema.sgb.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping("/listar")
    public ResponseEntity<List<AutorDTO>> listarTodos(){
        List<AutorDTO> autores = service.listarTodos();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> listarPorId(@PathVariable Long id) {
        AutorDTO autor = service.listarPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> incluir(@RequestBody AutorDTO dto) {
        AutorDTO autor = service.incluir(dto);
        return ResponseEntity.status(201).body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizar(@PathVariable Long id, @RequestBody AutorDTO dto) {
        AutorDTO autorAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(autorAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
