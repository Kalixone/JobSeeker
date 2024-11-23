package com.cvenjoyer.cv_enjoyer.dto;

import java.math.BigDecimal;

public record UpdateSalaryRequestDto(
        BigDecimal salary
) {
}
