package io.mohajistudio.tangerine.prototype.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDTO {
    private final String message;
    private Object data;
}
