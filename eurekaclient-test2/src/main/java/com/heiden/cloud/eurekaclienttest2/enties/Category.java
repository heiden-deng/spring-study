package com.heiden.cloud.eurekaclienttest2.enties;

public class Category {
    private Integer id;

    private String categoryName;

    private Integer parentId;

    private Integer fieldSort;

    private Long createTime;

    private Boolean isDeleted;

    public Integer getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(Integer fieldSort) {
        this.fieldSort = fieldSort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
