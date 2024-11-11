package com.sistema.sgb.repository;

import com.sistema.sgb.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    boolean existsByNome(String nome);
}
