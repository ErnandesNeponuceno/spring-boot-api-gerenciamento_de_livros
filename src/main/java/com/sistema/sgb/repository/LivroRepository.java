package com.sistema.sgb.repository;

import com.sistema.sgb.model.Livro;
import com.sistema.sgb.dto.LivroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByIsbn(String isbn);


}
