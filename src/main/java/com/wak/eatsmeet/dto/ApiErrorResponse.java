package com.wak.eatsmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private Map<String, String> errors;
    private int statusCode;
}
