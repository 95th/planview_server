package com.planview.server.entity;

public class ApiLogAggregate {
    private final String userName;
    private final String url;
    private final long count;

    public ApiLogAggregate(String userName, String url, long count) {
        this.userName = userName;
        this.url = url;
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public String getUrl() {
        return url;
    }

    public long getCount() {
        return count;
    }

}
