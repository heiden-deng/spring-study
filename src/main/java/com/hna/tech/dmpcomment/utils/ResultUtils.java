package com.hna.tech.dmpcomment.utils;

import com.github.pagehelper.PageInfo;
import com.hna.tech.dmpcomment.constant.Constant;
import com.hna.tech.dmpcomment.enums.ResponseStatusEnum;
import com.hna.tech.dmpcomment.vo.PageResultVo;
import com.hna.tech.dmpcomment.vo.ResponseVo;

import java.util.ArrayList;
import java.util.List;

public class ResultUtils {
    public static ResponseVo vo(int code, String message, Object data){
        return new ResponseVo<>(code,message,data);
    }
    public static ResponseVo error(int code,String message){
        return vo(code,message,null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum){
        return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
    }

    public static ResponseVo error(String message){
        return vo(Constant.DEFAULT_ERROR_CODE,message,null);
    }

    /**success**/
    public static ResponseVo success(String message,Object data){
        return vo(Constant.DEFAULT_SUCCESS_CODE,message,data);
    }

    public static ResponseVo success(String message){
        return success(message,null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum){
        return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
    }

    /**PageResultVo**/
    public static PageResultVo tablePage(Long total, List<?> list){
        return new PageResultVo(total,list);
    }

    public static PageResultVo tablePage(PageInfo pageInfo){
        if (pageInfo == null){
            return new PageResultVo(0L,new ArrayList());
        }
        return tablePage(pageInfo.getTotal(),pageInfo.getList());
    }
}
