package dev.matheuslf.desafio.inscritos.dto;

import java.util.Date;
import java.util.List;

public record ProjectRequestDto(

        String name,
        String description,
        Date startDate,
        Date endDate,
        List<TaskRequestDto> tasks

) {
}
