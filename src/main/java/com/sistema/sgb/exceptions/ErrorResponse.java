package com.sistema.sgb.exceptions;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Integer status;
    private String messagem;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.messagem = message;
    }

}