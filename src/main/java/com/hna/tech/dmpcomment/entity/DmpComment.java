package com.hna.tech.dmpcomment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DmpComment{
    //private static final long serialVersionUID = 767754740035356753L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = -1;
    private String title;
    private String content;
    private int pid = -1;
    private int company_id = -1;
    private int product_id = -1;
    private int user_id = -1;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    private int category = -1;
    private int status = -1;

    @Transient
    public List<DmpComment> subComments;




}
