package com.planview.server.entity;

public class RequestLogAggregate {
    private final String userId;
    private final String url;
    private final long count;

    public RequestLogAggregate(String userId, String url, long count) {
        this.userId = userId;
        this.url = url;
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public long getCount() {
        return count;
    }

}
