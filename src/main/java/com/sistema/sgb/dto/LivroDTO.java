package com.sistema.sgb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDTO {

    private Long   id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    @NotBlank
    private String autor;

    @NotBlank
    private String categoria;

}