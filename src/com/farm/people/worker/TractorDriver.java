package com.farm.people.worker;

import com.farm.farm.Farm;

public class TractorDriver extends Worker{
    @Override
    public void work(Farm farm) {
        if (!farm.getTractor().isAtCropStorage() && farm.getTractor().isFull()) {
            farm.getTractor().setAtCropStorage(true);
        } else if (farm.getTractor().isAtCropStorage() && !farm.getTractor().isFull()) {
            farm.getTractor().setAtCropStorage(false);
        }
    }

    @Override
    public String toString() {
        return "tractor driver";
    }

}
