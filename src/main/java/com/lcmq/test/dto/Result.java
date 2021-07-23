package com.lcmq.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 7802474074055391977L;

    private int code = 0;
    private String message = "success";
    private T data;


    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
