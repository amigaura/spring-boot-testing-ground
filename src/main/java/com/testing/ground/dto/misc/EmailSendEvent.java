package com.testing.ground.dto.misc;

import lombok.Data;

@Data
public class EmailSendEvent {
    private final Object source;
    private final Long requestId;
}
