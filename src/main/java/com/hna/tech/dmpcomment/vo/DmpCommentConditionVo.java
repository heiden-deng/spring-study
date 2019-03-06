package com.hna.tech.dmpcomment.vo;

import com.hna.tech.dmpcomment.entity.DmpComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DmpCommentConditionVo extends BaseConditionVo{
    private DmpComment dmpComment;
}
