package com.farm.people.decorator;

import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public class LoadTractor extends WorkTypeDecorator {

    public LoadTractor(Worker worker) {
        super(worker);
    }

    @Override
    public void work(Farm farm) {
        super.work(farm);
    }

    @Override
    public void Update() {
        super.update();
    }

    @Override
    public String toString() {
        return "LoadTractor " + decoratedWorker.toString();
    }
}
