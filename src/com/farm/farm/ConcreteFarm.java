package com.farm.farm;

import com.farm.crop.CornCrop;
import com.farm.crop.Crop;

public class ConcreteFarm extends Farm {
    public ConcreteFarm(int maxWorkers) {
        super(maxWorkers);
    }

    @Override
    public Crop generateCrop() {
        return new CornCrop(100.0); // Replace with appropriate crop generation logic
    }
}
