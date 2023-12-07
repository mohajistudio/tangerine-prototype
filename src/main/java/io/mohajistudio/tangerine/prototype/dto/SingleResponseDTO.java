package io.mohajistudio.tangerine.prototype.dto;

import lombok.*;

@Getter
@Builder
public class SingleResponseDTO {
    private final String message;
    private Object data;

    public SingleResponseDTO(String message) {
        this.message = message;
    }

    public SingleResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
