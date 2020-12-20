package com.example.worker.api;

import com.example.worker.*;
import com.example.worker.model.Worker;
import com.example.worker.model.Position;
import com.example.worker.service.WorkerService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class GrpcWorkerController extends WorkerServiceGrpc.WorkerServiceImplBase {

    @Autowired
    private WorkerService workerService;

    @Override
    public void add(WorkerRequest request, StreamObserver<WorkerResponse> responseObserver) {
        String workerId = request.getId();
        String name = request.getName();
        String surname = request.getSurname();
        Position position = Position.valueOf(request.getPosition().toString());

        Worker workerAdd = new Worker(workerId, name, surname, position);
        Worker workerResponse = workerService.addWorker(workerAdd);

        WorkerResponse response = WorkerResponse.newBuilder()
                                                .setId(workerResponse.getWorkerId())
                                                .setName(workerResponse.getName())
                                                .setSurname(workerResponse.getSurname())
                                                .setPosition(com.example.worker.Position.valueOf(workerResponse.getPosition().toString()))
                                                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void all(AllWorkersRequest request, StreamObserver<AllWorkersResponse> responseObserver) {
        List<Worker> workers = workerService.getAll();
        List<WorkerResponse> responses = new ArrayList<>();

        for(Worker worker: workers){
            WorkerResponse oneResponse = WorkerResponse.newBuilder()
                                                        .setId(worker.getWorkerId())
                                                        .setName(worker.getName())
                                                        .setSurname(worker.getSurname())
                                                        .setPosition(com.example.worker.Position.valueOf(worker.getPosition().toString()))
                                                        .build();
            responses.add(oneResponse);
        }
        AllWorkersResponse response = AllWorkersResponse.newBuilder().addAllWorkers(responses).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(WorkerByIdRequest request, StreamObserver<WorkerResponse> responseObserver) {
        Worker worker = workerService.getWorkerById(request.getId());

        WorkerResponse response = WorkerResponse.newBuilder()
                                                .setId(worker.getWorkerId())
                                                .setName(worker.getName())
                                                .setSurname(worker.getSurname())
                                                .setPosition(com.example.worker.Position.valueOf(worker.getPosition().toString()))
                                                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(WorkerByIdRequest request, StreamObserver<DeleteWorkerResponse> responseObserver) {
        workerService.deleteById(request.getId());
        DeleteWorkerResponse response = DeleteWorkerResponse.newBuilder()
                                                            .setResponse("Worker with id "+request.getId()+" was deleted")
                                                            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
