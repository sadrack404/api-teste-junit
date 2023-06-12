package br.com.dicasdeumdev.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timesamp;
    private Integer status;
    private String error;
    private String path;
}