package com.w2s.orhan.where2stay.Chat;

public class Messages {
    private String message;
    private Long time;
    private String from;

    public Messages(String message, Long time) {
        this.message = message;
        this.time = time;
    }

    public Messages() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
