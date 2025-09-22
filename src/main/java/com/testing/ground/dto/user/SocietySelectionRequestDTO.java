package com.testing.ground.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocietySelectionRequestDTO {
    private Long userId;
    private Long mappingId;
}
