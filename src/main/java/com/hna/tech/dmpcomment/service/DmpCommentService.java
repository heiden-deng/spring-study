package com.hna.tech.dmpcomment.service;

import com.github.pagehelper.PageInfo;
import com.hna.tech.dmpcomment.entity.DmpComment;
import com.hna.tech.dmpcomment.vo.DmpCommentConditionVo;

import java.util.List;

public interface DmpCommentService {
    public DmpComment getDmpCommentById(int id);
    public List<DmpComment> getDmpCommentByUserId(int userId);
    public List<DmpComment> getDmpCommentByProductId(int productId);
    public List<DmpComment> getSubComment(int pid);
    public List<DmpComment> queryComments(DmpComment comment);
    public void addComment(DmpComment dmpComment);
    public void addSelectiveComment(DmpComment dmpComment);
    public PageInfo<DmpComment> findPageBreakByCondition(DmpCommentConditionVo dmpCommentConditionVo);
    public void deleteComment(int id);
}
