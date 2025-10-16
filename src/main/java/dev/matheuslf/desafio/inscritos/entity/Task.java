package dev.matheuslf.desafio.inscritos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "tb_tasks")
public class Task {

    public Task() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false, length = 150)
    @Size(min = 5, max = 150)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

}
