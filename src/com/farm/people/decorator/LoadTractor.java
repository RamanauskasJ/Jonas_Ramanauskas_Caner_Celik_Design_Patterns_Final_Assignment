package com.farm.people.decorator;

import com.farm.crop.Crop;
import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public class LoadTractor extends WorkTypeDecorator{

    public LoadTractor(Worker worker) {
        super(worker);
    }

    public void work(Farm farm) {
        worker.work(farm);
        Crop nextCrop = farm.getUnloadedCrops().peek();

        worker.decreaseHungerMeter(10);
        worker.decreaseSanityMeter(20);
        worker.increaseTimesWorked();

        if (!farm.getTractor().isAtCropStorage()) {
            if (!farm.getTractor().isFull()) {
                farm.getTractor().loadTractor(nextCrop);
                farm.getUnloadedCrops().remove(nextCrop);
            }
        }
    }

    @Override
    public String toString() {
        return "Tractor loader";
    }

}
