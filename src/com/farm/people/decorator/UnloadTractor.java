package com.farm.people.decorator;

import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public class UnloadTractor extends WorkTypeDecorator{
    public UnloadTractor(Worker worker) {
        super(worker);
    }

    @Override
    public void work(Farm farm) {
        worker.work(farm);
        if (farm.getTractor().isAtCropStorage()) {
            farm.getTractor().unloadTractor();
        }
    }

    @Override
    public String toString() {
        return "Tractor unLoader";
    }
}
