package com.rest.webservice.provider.utils.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResponse {
    private Date timestamp;
    private String message;
    private Map<String, String> errors;
}
