package com.jwt_security_project.controller;
public class ApiResponse {
    private int status;   // for HTTP status code
    private String message;   // for message to be displayed to the user
    private Object data;  // The employee data or response data
    private int page;  // Follow camelCase convention
    private int totalPages;
    private long totalElements;

    public ApiResponse(int status, String message, Object data, int page, int totalPages, long totalElements) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.page = 0;
        this.totalPages = 0;
        this.totalElements = 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}