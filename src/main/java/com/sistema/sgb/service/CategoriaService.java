package com.sistema.sgb.service;

import com.sistema.sgb.dto.CategoriaDTO;
import com.sistema.sgb.exceptions.BusinessException;
import com.sistema.sgb.model.Categoria;
import com.sistema.sgb.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaDTO> listarTodos() {
        List<Categoria> categorias = repository.findAll();
        return categorias.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CategoriaDTO listarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        return convertToDTO(categoria);
    }

    public CategoriaDTO incluir(CategoriaDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new BusinessException("Categoria com o mesmo nome já existe!");
        }

        validarDados(dto);
        Categoria categoria = convertToEntity(dto);
        Categoria categoriaSalva = repository.save(categoria);
        return convertToDTO(categoriaSalva);
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));

        if (repository.existsByNome(dto.getNome()) && !categoria.getNome().equals(dto.getNome())) {
            throw new BusinessException("Outra categoria com o mesmo nome já existe!");
        }
        validarDados(dto);
        categoria.setNome(dto.getNome());
        Categoria categoriaAtualizada = repository.save(categoria);
        return convertToDTO(categoriaAtualizada);
    }

    public void deletar(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        try{
            repository.delete(categoria);
        } catch (DataIntegrityViolationException ex){
            throw new BusinessException("Não é possível excluir a categoria porque existem livros associados a ela.");
        }

    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        return Categoria.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .build();
    }

    private void validarDados(CategoriaDTO dto){
        if (dto.getNome() == null || dto.getNome().isEmpty() ) {
            throw new BusinessException("Nome da Categoria não pode ser nulo ou vazio!");
        }
    }


}
