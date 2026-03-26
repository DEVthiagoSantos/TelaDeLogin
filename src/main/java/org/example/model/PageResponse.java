package org.example.model;

import java.util.List;

public class PageResponse<T> {

    private List<T> data;
    private int currentPage;
    private long totalPages;
    private int totalItems;

    public PageResponse(List<T> data,
                        int currentPage,
                        long totalPages,
                        int totalItems) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<T> getData() {
        return data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
