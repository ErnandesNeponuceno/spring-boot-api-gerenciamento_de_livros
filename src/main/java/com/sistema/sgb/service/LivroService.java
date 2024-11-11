package com.sistema.sgb.service;

import com.sistema.sgb.exceptions.BusinessException;
import com.sistema.sgb.model.Autor;
import com.sistema.sgb.model.Categoria;
import com.sistema.sgb.model.Livro;
import com.sistema.sgb.repository.AutorRepository;
import com.sistema.sgb.repository.CategoriaRepository;
import com.sistema.sgb.repository.LivroRepository;
import com.sistema.sgb.dto.LivroDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<LivroDTO> listarTodos() {
    List<Livro> livros = repository.findAll();
    return livros.stream()
            .map(this::convertToDTO)
            .toList();
    }

    public LivroDTO listarPorId(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public LivroDTO incluir(LivroDTO dto) {
        validarDados(dto);
        Livro livro = convertToEntity(dto);
        Livro livroSalvo = repository.save(livro);
        return convertToDTO(livroSalvo);
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

        Autor autor = autorRepository.findById(Long.valueOf(dto.getAutor()))
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));

        Categoria categoria = categoriaRepository.findById(Long.valueOf(dto.getCategoria()))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));

        validarAtualizacao(dto);
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        Livro livroAtualizado = repository.save(livro);
        return convertToDTO(livroAtualizado);
    }

    public void deletar(Long id) throws Exception {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));
        try {
            repository.delete(livro);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    private LivroDTO convertToDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .isbn(livro.getIsbn())
                .autor(livro.getAutor().getNome())
                .categoria(livro.getCategoria().getNome())
                .build();
    }

    private Livro convertToEntity(LivroDTO dto) {

        Autor autor = autorRepository.findById(Long.valueOf(dto.getAutor()))
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));

        Categoria categoria = categoriaRepository.findById(Long.valueOf(dto.getCategoria()))
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));

        return Livro.builder()
                .titulo(dto.getTitulo())
                .isbn(dto.getIsbn())
                .autor(autor)
                .categoria(categoria)
                .build();
    }

    private void validarDados(LivroDTO dto){
        if (dto.getTitulo() == null || dto.getTitulo().isEmpty() ) {
            throw new BusinessException("Titulo não pode ser vazio ou nulo!");
        }

        if (repository.existsByTitulo(dto.getTitulo())) {
            throw new BusinessException("Já existe um livro com o título: " + dto.getTitulo());
        }

        if (dto.getIsbn() == null || dto.getIsbn().isEmpty() ) {
            throw new BusinessException("O registro ISBN não pode ser vazio ou nulo!");
        }

        if (repository.existsByIsbn(dto.getIsbn())) {
            throw new BusinessException("Livro com ISBN já cadastrado!");
        }

        if (dto.getAutor() == null || dto.getAutor().isEmpty()) {
            throw new BusinessException("O autor do livro é obrigatório.");
        }

        if (dto.getCategoria() == null || dto.getCategoria().isEmpty()) {
            throw new BusinessException("A categoria do livro é obrigatório.");
        }

    }

    private void validarAtualizacao(LivroDTO dto){
        if (dto.getTitulo() == null || dto.getTitulo().isEmpty()) {
            throw new BusinessException("O título do livro é obrigatório.");
        }
        if (dto.getIsbn() == null || dto.getIsbn().isEmpty()) {
            throw new BusinessException("O ISBN do livro é obrigatório.");
        }
    }


}

