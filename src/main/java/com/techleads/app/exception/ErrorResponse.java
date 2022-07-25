package com.techleads.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;
}
