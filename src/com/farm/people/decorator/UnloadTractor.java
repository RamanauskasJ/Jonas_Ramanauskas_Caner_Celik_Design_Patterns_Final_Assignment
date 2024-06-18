package com.farm.people.decorator;

import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public class UnloadTractor extends WorkTypeDecorator {

    public UnloadTractor(Worker worker) {
        super(worker);
    }

    @Override
    public void work(Farm farm) {
        super.work(farm);
        // Additional implementation for UnloadTractor work
    }

    @Override
    public void Update() {
        super.update();
    }

    @Override
    public String toString() {
        return "UnloadTractor " + decoratedWorker.toString();
    }
}

