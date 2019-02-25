package com.example.servicedemo.controller;

import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Policy {
    @NotNull
    private Long refreshInterval = 60L;

    private Long limit;

    private Long quota;

    @NotNull
    private List<Type> type = Lists.newArrayList();

    public enum Type {
        ORIGIN, USER, URL
    }
}
