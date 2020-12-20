package com.example.worker.service;

import com.example.worker.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker addWorker(Worker newWorker) {
        return workerRepository.save(newWorker);
    }

    @Override
    public List<Worker> getAll() {
        return (List<Worker>) workerRepository.findAll();
    }

    @Override
    public Worker getWorkerById(String id) {
        return workerRepository.getOne(id);
    }

    @Override
    public void deleteById(String id) {
        workerRepository.deleteById(id);
    }
}

