package com.sistema.sgb.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tbl_autores")
@Entity(name = "Autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nacionalidade")
    private String nacionalidade;

}
