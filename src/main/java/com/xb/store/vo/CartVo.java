package com.xb.store.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String image;
    private String title;
    private Long realPrice;
}
