package com.hna.tech.dmpcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hna.tech.dmpcomment.dao.DmpCommentMapper;
import com.hna.tech.dmpcomment.entity.DmpComment;
import com.hna.tech.dmpcomment.service.DmpCommentService;
import com.hna.tech.dmpcomment.vo.DmpCommentConditionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DmpCommentServiceImpl implements DmpCommentService{

    @Autowired
    private DmpCommentMapper dmpCommentMapper;

    @Override
    public DmpComment getDmpCommentById(int id) {
        return dmpCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DmpComment> getDmpCommentByUserId(int userId) {
        return dmpCommentMapper.selectByUserId(userId);
    }

    @Override
    public List<DmpComment> getDmpCommentByProductId(int productId) {
        return dmpCommentMapper.selectByProductId(productId);
    }

    @Override
    public List<DmpComment> getSubComment(int pid) {
        return dmpCommentMapper.selectSubComment(pid);
    }

    @Override
    public List<DmpComment> queryComments(DmpComment comment) {
        comment.setCategory(0);
        List<DmpComment> comments = dmpCommentMapper.selectComments(comment);
        for (DmpComment comment1: comments){
            List<DmpComment> subComments = dmpCommentMapper.selectSubComment(comment1.getId());
            comment1.subComments = new ArrayList<>();
            if (!CollectionUtils.isEmpty(subComments)){
                comment1.subComments.addAll(subComments);
            }

        }
        return comments;
    }

    @Override
    public void addComment(DmpComment dmpComment) {
        dmpComment.setCreate_time(new Date());
        dmpCommentMapper.insert(dmpComment);
    }

    @Override
    public void addSelectiveComment(DmpComment dmpComment) {
        dmpCommentMapper.insertSelective(dmpComment);
    }

    @Override
    public PageInfo<DmpComment> findPageBreakByCondition(DmpCommentConditionVo dmpCommentConditionVo) {
        PageHelper.startPage(dmpCommentConditionVo.getPageNum(),dmpCommentConditionVo.getPageSize());
        List<DmpComment> dmpComments = dmpCommentMapper.findPageBreakByCondition(dmpCommentConditionVo);
        if (CollectionUtils.isEmpty(dmpComments)){
            return null;
        }
        PageInfo<DmpComment> pageInfo = new PageInfo<>(dmpComments);
        return pageInfo;
    }

    @Override
    public void deleteComment(int id) {
        dmpCommentMapper.delete(id);
    }
}
