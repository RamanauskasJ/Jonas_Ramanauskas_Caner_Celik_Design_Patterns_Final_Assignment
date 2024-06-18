package com.farm.farm;

import com.farm.crop.Crop;
import com.farm.crop.OatCrop;

public class OatField extends Farm {
    public OatField(int maxWorkers) {
        super(maxWorkers);
    }

    @Override
    public Crop generateCrop() {
        return new OatCrop(100.0); // Adjust the parameters as needed
    }
}
