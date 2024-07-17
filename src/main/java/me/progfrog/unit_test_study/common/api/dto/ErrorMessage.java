package me.progfrog.unit_test_study.common.api.dto;

import java.time.LocalDateTime;

public record ErrorMessage(
        Integer statusCode,
        String message,
        LocalDateTime timestamp
) { }
