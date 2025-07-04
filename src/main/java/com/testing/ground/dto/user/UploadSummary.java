package com.testing.ground.dto.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UploadSummary {
    private int total;
    private int success;
    private int failed;
    private List<String> errorLines = new ArrayList<>();
}

