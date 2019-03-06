package com.hna.tech.dmpcomment.controller;


import com.hna.tech.dmpcomment.entity.DmpComment;
import com.hna.tech.dmpcomment.service.DmpCommentService;
import com.hna.tech.dmpcomment.utils.ResultUtils;
import com.hna.tech.dmpcomment.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class DmpCommentController {

    private static final Logger log= LoggerFactory.getLogger(DmpCommentController.class);

    @Autowired
    private DmpCommentService dmpCommentService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(@RequestBody DmpComment dmpComment){
       try{
            dmpCommentService.addComment(dmpComment);
            return ResultUtils.success("添加内容成功");
       }catch (Exception e){
            log.error("[用户内容失败] " + e.getMessage());
            return ResultUtils.error("添加内容失败");
       }

    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseVo<List<DmpComment>> queryComment(@RequestBody DmpComment comment){
        try{
            return ResultUtils.success("查询内容成功",dmpCommentService.queryComments(comment));
        }catch (Exception e){
            log.error("[查询内容失败] " + e.getMessage());
            return ResultUtils.error("查询内容失败");
        }
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseVo deleteComment(@PathVariable("id")  Integer id){
        try{
            dmpCommentService.deleteComment(id);
            return ResultUtils.success("删除内容成功");
        }catch (Exception e){
            log.error("[删除内容失败] " + e.getMessage());
            return ResultUtils.error("删除内容失败");
        }
    }
}
