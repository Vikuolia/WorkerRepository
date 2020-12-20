package com.example.worker.service;

import com.example.worker.model.Worker;

import java.util.List;

public interface WorkerService {

    Worker addWorker(Worker newWorker);

    List<Worker> getAll();

    Worker getWorkerById(String id);

    void deleteById(String id);
}
