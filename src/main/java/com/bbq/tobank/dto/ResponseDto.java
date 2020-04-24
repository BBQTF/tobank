package com.bbq.tobank.dto;

import lombok.Data;

/**
 * @author liutf
 * @date 2020-04-24
 */
@Data
public class ResponseDto {
    private String code;

    public ResponseDto(String code) {
        this.code = code;
    }

    public ResponseDto() {
    }
}
