package com.farm.people.worker;

import com.farm.crop.Crop;
import com.farm.farm.Farm;

public class Farmer extends Worker {

    public Farmer() {
        super();
    }

    @Override
    public String toString() {
        return "Farmer";
    }

    @Override
    public void work(Farm farm) {
        performWork(farm);
    }

    @Override
    public void Update() {
        this.doTask();
    }

    @Override
    public void performWork(Farm farm) {
        Crop crop = farm.generateCrop();
        farm.harvestCrops();
        farm.getTractor().loadTractor(crop);
        increaseTimesWorked();
        decreaseHungerMeter(10);
        decreaseSanityMeter(5);
    }
}
