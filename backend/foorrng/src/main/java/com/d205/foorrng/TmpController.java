package com.d205.foorrng;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter @Setter
@RestController
@RequiredArgsConstructor
public class TmpController {

    @GetMapping("/tmp")
    public String tmp() {
        return "success";
    }
}
