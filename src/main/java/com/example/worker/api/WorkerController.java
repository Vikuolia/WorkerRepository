package com.example.worker.api;

import com.example.worker.model.Worker;
import com.example.worker.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public Worker addWorker(@RequestBody Worker worker){
        return workerService.addWorker(worker);
    }

    @GetMapping
    public List<Worker> getAllWorkers(){
        return workerService.getAll();
    }

    @DeleteMapping("{workerId}")
    public ResponseEntity<Void> deleteWorkerById(@PathVariable String workerId){
        workerService.deleteById(workerId);
        return ResponseEntity.noContent().build();
    }
}
