package com.alevel.web.controller.personal;

import com.alevel.validated.ValidId;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("/personal/dashboard")
public class PersonalController {

    @GetMapping
    public String dashboard() {
        return "pages/personal/dashboard";
    }

    @GetMapping("/{id}")
    public String dashboard(@PathVariable @ValidId(message = "id must be more than zero") Long id) {
        return "pages/personal/dashboard";
    }
}
