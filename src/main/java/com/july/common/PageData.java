package com.july.common;

import java.util.Collection;
import java.util.List;

/**
 * @author July
 * @date 2017/12/19
 */
public class PageData<T> {
    private int pageSize;
    private int pageNo;
    private int total;
    private Collection<T> data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }
}
