package com.sistema.sgb.service;

import com.sistema.sgb.dto.AutorDTO;
import com.sistema.sgb.exceptions.BusinessException;
import com.sistema.sgb.model.Autor;
import com.sistema.sgb.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public List<AutorDTO> listarTodos() {
        List<Autor> autores = repository.findAll();
        return autores.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public AutorDTO listarPorId(Long id){
            Autor autor = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));
            return convertToDTO(autor);
    }

    public AutorDTO incluir(AutorDTO dto) {
        if (repository.existsByNome(dto.getNome())) {
            throw new BusinessException("Existe outro autor com o mesmo nome!");
        }
        validarDados(dto);
        Autor autor = convertToEntity(dto);
        Autor autorSalvo = repository.save(autor);
        return convertToDTO(autorSalvo);
    }

    public AutorDTO atualizar(Long id, AutorDTO dto) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));

        if (repository.existsByNome(dto.getNome()) && !autor.getNome().equals(dto.getNome())) {
            throw new BusinessException("Existe outro autor com o mesmo nome!");
        }

        validarDados(dto);

        autor.setNome(dto.getNome());
        autor.setNacionalidade(dto.getNacionalidade());
        Autor autorAtualizado = repository.save(autor);
        return convertToDTO(autorAtualizado);
    }

    public void deletar(Long id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado!"));
        try {
            repository.delete(autor);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException("Não é possível excluir o autor porque existem livros associados a ele.");
        }
    }

    private void validarDados (AutorDTO dto){

        if (dto.getNome() == null || dto.getNome().isEmpty() ) {
            throw new BusinessException("Nome não pode ser nulo ou vazio!");
        }

        if (dto.getNacionalidade() == null || dto.getNacionalidade().isEmpty()){
            throw new BusinessException("Nacionalidade não pode ser nulo ou vazio!");
        }
    }

    private AutorDTO convertToDTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .build();
    }

    private Autor convertToEntity(AutorDTO dto) {
        return Autor.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .nacionalidade(dto.getNacionalidade())
                .build();
    }
}
