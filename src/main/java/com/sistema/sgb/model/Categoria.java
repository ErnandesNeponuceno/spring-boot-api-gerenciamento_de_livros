package com.sistema.sgb.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tbl_categorias")
@Entity(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

}
