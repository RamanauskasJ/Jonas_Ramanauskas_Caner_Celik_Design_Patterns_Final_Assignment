package com.farm.farm;

import com.farm.crop.CornCrop;
import com.farm.crop.Crop;

public class CornField extends Farm {
    public CornField(int maxWorkers) {
        super(maxWorkers);
    }

    @Override
    public Crop generateCrop() {
        return new CornCrop(100.0); // Adjust the parameters as needed
    }
}
