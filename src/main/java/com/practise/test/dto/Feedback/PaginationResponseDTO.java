package com.practise.test.dto.Feedback;

import java.util.List;

public class PaginationResponseDTO<T> {
    private List<T> items;          // Danh sách các đối tượng (ví dụ: danh sách feedbacks)
    private int currentPage;        // Trang hiện tại
    private int limit;              // Số lượng bản ghi mỗi trang
    private long totalItems;        // Tổng số bản ghi
    private int totalPages;         // Tổng số trang

    // Constructor
    public PaginationResponseDTO(List<T> items, int currentPage, int limit, long totalItems, int totalPages) {
        this.items = items;
        this.currentPage = currentPage;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    // Getters and Setters
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
