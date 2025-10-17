package dev.matheuslf.desafio.inscritos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public record ProjectRequestDto(

        @NotBlank(message = "O nome do projeto é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,
        String description,
        @NotNull(message = "A data de início é obrigatória.")
        Date startDate,
        @FutureOrPresent(message = "A data limite da tarefa não pode ser no passado.")
        Date endDate,

        List<@Valid TaskRequestDto> tasks

) {
}
