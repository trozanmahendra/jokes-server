package com.cgi.jokes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class ApiResponse {
    private String status;
    private String message;
    private Object obj;
}
