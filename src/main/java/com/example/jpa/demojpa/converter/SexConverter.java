package com.example.jpa.demojpa.converter;

import com.example.jpa.demojpa.enumeration.SexEnum;

import javax.persistence.AttributeConverter;


public class SexConverter implements AttributeConverter<SexEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getEnumById(integer);
    }
}
