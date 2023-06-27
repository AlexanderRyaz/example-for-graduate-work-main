package ru.skypro.homework.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String path;
}

