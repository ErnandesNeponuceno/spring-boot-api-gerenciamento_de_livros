package com.sistema.sgb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String nacionalidade;

}
