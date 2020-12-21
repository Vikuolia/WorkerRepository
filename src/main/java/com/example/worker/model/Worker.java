package com.example.worker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@EnableAutoConfiguration
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "worker")
public class Worker {

    @Id
    private String workerId;

    private String name;
    private String surname;
    private Position position;

    public Worker(String name, String surname, Position position){
        this.workerId = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.position = position;
    }
}

