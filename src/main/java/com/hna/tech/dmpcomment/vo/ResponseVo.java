package com.hna.tech.dmpcomment.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hna.tech.dmpcomment.enums.ResponseStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseVo<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVo(Integer status,String message,T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }

    public ResponseVo(ResponseStatusEnum status, T data){
        this(status.getCode(),status.getMessage(),data);
    }

    public  String toJson(){
        T t=this.getData();
        if(t instanceof List || t instanceof Collection){
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        }else {
            return JSONObject.toJSONString(this,SerializerFeature.WriteMapNullValue);
        }
    }
}