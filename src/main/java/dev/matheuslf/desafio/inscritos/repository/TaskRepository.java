package dev.matheuslf.desafio.inscritos.repository;

import dev.matheuslf.desafio.inscritos.entity.Priority;
import dev.matheuslf.desafio.inscritos.entity.Status;
import dev.matheuslf.desafio.inscritos.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
            SELECT t From Task t
            WHERE (:status IS NULL OR t.status = :status)
            AND (:priority IS NULL OR t.priority = :priority)
            AND (:projectId IS NULL OR t.project.id = :projectId)
            """)
    List<Task> findByFilters(
            @Param("status") Status status,
            @Param("priority") Priority priority,
            @Param("projectId") Long projectId
            );

}
