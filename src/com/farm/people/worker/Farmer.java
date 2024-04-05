package com.farm.people.worker;

import com.farm.farm.Farm;

public class Farmer extends Worker{

    public Farmer() {
    }

    @Override
    public void work(Farm farm) {
        farm.harvestCrops();
    }

    @Override
    public String toString() {
        return "farmer";
    }
}
