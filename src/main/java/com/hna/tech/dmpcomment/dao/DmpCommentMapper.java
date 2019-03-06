package com.hna.tech.dmpcomment.dao;

import com.hna.tech.dmpcomment.entity.DmpComment;
import com.hna.tech.dmpcomment.vo.DmpCommentConditionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface DmpCommentMapper {
    public DmpComment selectByPrimaryKey(int id);
    public List<DmpComment> selectByUserId(int user_id);
    public List<DmpComment> selectSubComment(int pid);
    public List<DmpComment> selectByProductId(int product_id);
    public List<DmpComment> selectComments(DmpComment dmpComment);
    public List<DmpComment> findPageBreakByCondition(DmpCommentConditionVo dmpCommentConditionVo);
    public void insert(DmpComment dmpComment);
    public void insertSelective(DmpComment dmpComment);
    public void delete(int id);

}
