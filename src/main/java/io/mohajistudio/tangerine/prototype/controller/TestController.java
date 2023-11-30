package io.mohajistudio.tangerine.prototype.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "테스트 API")
@RestController
public class TestController {

    @GetMapping("/")
    String getRoot() {
        return "Hello World";
    }
}
