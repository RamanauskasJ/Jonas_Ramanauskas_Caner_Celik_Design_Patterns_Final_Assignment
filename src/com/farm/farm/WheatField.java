package com.farm.farm;

import com.farm.crop.Crop;
import com.farm.crop.WheatCrop;

public class WheatField extends Farm {
    public WheatField(int maxWorkers) {
        super(maxWorkers);
    }

    @Override
    public Crop generateCrop() {
        return new WheatCrop(100.0); // Adjust the parameters as needed
    }
}
