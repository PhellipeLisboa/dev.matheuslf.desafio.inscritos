package dev.matheuslf.desafio.inscritos.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvalidTaskDueDateException extends RuntimeException{

    public InvalidTaskDueDateException(Date projectStartDate, Date projectEndDate) {
        super(
                String.format(
                        "A data limite da tarefa não pode ser anterior ao início (%s) ou posterior ao término " +
                "(%s) do projeto",
                        new SimpleDateFormat("dd/MM/yyyy").format(projectStartDate),
                        new SimpleDateFormat("dd/MM/yyyy").format(projectEndDate)
                )
        );
    }

}
