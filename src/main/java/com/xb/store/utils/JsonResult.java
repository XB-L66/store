package com.xb.store.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    private E data;
}
