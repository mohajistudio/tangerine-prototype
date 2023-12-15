package io.mohajistudio.tangerine.prototype.domain.post.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    @NotNull
    private Long id;

    @Getter
    @Setter
    public static class Details extends CategoryDTO {
        private String name;
    }

    @Getter
    public static class Add extends CategoryDTO {
    }
}
