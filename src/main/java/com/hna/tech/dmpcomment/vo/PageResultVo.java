package com.hna.tech.dmpcomment.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageResultVo {
    private Long total;
    private List rows;

    public PageResultVo(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResultVo() {
    }
}
