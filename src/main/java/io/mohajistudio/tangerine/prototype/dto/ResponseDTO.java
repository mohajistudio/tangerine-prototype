package io.mohajistudio.tangerine.prototype.dto;

import lombok.*;

@Getter
@Builder
public class ResponseDTO {
    private final String message;
    private Object data;

    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
