package com.gagauz.demo.observation.web.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String message;
    private String exceptionClass;
    private String stackTrace;
}
