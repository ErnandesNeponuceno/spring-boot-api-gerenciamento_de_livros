package com.sistema.sgb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;

    @NotBlank
    private String nome;

}
