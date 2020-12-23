package com.icis.pojo;

import java.util.List;

/**
 * xiejiajian     2020/10/28 14:39
 */
public class PageBean<T> {
    // 当前页数
    private Integer currentPage;
    // 页面记录大小
    private Integer pageSize;
    // 数据列表
    private List<Route> dataList;
    // 总记录数
    private Integer totalCount;
    // 总页数
    private Integer totalPage;


    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Route> getDataList() {
        return dataList;
    }

    public void setDataList(List<Route> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public PageBean(Integer currentPage, Integer pageSize, List<Route> dataList, Integer totalCount, Integer totalPage) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.dataList = dataList;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public PageBean() {
    }
}
