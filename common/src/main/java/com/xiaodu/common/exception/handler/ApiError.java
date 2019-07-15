package com.xiaodu.common.exception.handler;

import lombok.Data;

/**
 * @author shen
 * @date 2019-4-22
 */
@Data
class ApiError {

    private Integer status;
    private String message;

    public ApiError(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}


