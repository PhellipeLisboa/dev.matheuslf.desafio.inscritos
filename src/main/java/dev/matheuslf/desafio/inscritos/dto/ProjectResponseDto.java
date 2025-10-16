package dev.matheuslf.desafio.inscritos.dto;

import java.util.Date;
import java.util.List;

public record ProjectResponseDto(

        Long id,
        String name,
        String description,
        Date startDate,
        Date endDate,
        List<TaskResponseDto> tasks

) {
}
