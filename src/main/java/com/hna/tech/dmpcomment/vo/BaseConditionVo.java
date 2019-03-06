package com.hna.tech.dmpcomment.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseConditionVo {

    public final static int DEFAULT_PAGE_SIZE=10;
    private int pageNum=1;
    private int pageSize=0;
    private int pageStart=0;
    private String orderField;
    private String orderDirection;
    private String keywords;


    public int getPageSize() {
        return pageSize >0 ? pageSize:DEFAULT_PAGE_SIZE;
    }

    public int getPageStart() {
        return pageNum> 0 ?(pageNum-1) *pageSize:0;
    }
}

