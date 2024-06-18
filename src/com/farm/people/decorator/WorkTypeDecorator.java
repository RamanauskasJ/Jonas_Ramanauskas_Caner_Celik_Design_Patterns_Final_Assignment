package com.farm.people.decorator;

import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public abstract class WorkTypeDecorator extends Worker {
    protected Worker decoratedWorker;

    public WorkTypeDecorator(Worker worker) {
        this.decoratedWorker = worker;
    }

    @Override
    public void work(Farm farm) {
        decoratedWorker.work(farm);
    }

    @Override
    public void Update() {
        decoratedWorker.update();
    }

    @Override
    public String toString() {
        return decoratedWorker.toString();
    }
}
