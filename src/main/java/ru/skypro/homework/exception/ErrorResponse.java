package ru.skypro.homework.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorResponse {
    Date timestamp;
    int status;
    String error;
    String path;
}

