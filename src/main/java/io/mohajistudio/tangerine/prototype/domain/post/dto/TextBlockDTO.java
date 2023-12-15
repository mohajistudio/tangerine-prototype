package io.mohajistudio.tangerine.prototype.domain.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextBlockDTO {
    @NotNull
    private short orderNumber;
    @NotNull
    private String content;

    @Getter
    @Setter
    public static class Details extends TextBlockDTO {
        private Long id;
    }

    @Getter
    @Setter
    public static class Add extends TextBlockDTO { }
}
