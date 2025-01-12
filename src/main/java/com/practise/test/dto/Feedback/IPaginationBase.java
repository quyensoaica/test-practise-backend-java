package com.practise.test.dto.Feedback;


public class IPaginationBase {
    private int page;
    private int limit;

    // Constructor, Getters, and Setters
    public IPaginationBase(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
