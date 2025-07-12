package com.testing.ground.dto.misc;

import lombok.Data;

//@Data
public class EmailSendEvent {
    private String to;
    private String subject;
    private String body;
    private Object source;
    private Long requestId;

    public EmailSendEvent() {
    }

    public EmailSendEvent(String to, String subject, String body, Object source, Long requestId) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.source = source;
        this.requestId = requestId;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Object getSource() {
        return source;
    }

    public Long getRequestId() {
        return requestId;
    }
}
