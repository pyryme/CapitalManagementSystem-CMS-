package com.xxx.cms.bean;


import java.util.ArrayList;

/**
 * 用于存储分页查询中总数据数 和 每一页内容的封装类
 * @author fangyufan
 * @param <T> 要获取的内容的类型
 */
public class PageBean<T> {
    /**
     * 总数据条目数
     */
    private Integer totalCount;
    /**
     * 当前页面的内容
     */
    private ArrayList<T> contents;

    public PageBean() {
    }

    public PageBean(Integer totalCount, ArrayList<T> contents) {
        this.totalCount = totalCount;
        this.contents = contents;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<T> getContents() {
        return contents;
    }

    public void setContents(ArrayList<T> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", contents=" + contents +
                '}';
    }
}
