package com.aris.limitsservice.controller;

import com.aris.limitsservice.configuration.Configuration;
import com.aris.limitsservice.model.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {

    private final Configuration configuration;

    @GetMapping("/limits")
    public Limits getLimits() {
        return new Limits((configuration.getMinimum()), configuration.getMaximum());
    }
}
