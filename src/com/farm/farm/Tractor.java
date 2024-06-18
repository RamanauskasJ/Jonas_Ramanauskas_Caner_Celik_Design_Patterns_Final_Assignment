package com.farm.farm;

import com.farm.crop.Crop;
import java.util.Queue;
import java.util.ArrayDeque;

public class Tractor {
    private final Queue<Crop> crops = new ArrayDeque<>();
    private boolean isFull;
    private boolean isAtCropStorage;

    public void loadTractor(Crop crop) {
        if (!isFull) {
            crops.add(crop);
            if (crops.size() >= 10) { // Assuming 10 is the capacity of the tractor
                isFull = true;
            }
        }
    }

    public Queue<Crop> unloadTractor() {
        Queue<Crop> unloadedCrops = new ArrayDeque<>(crops);
        crops.clear();
        isFull = false;
        isAtCropStorage = true;
        CropStorage.addCrops(unloadedCrops); // Add crops to storage
        return unloadedCrops;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isAtCropStorage() {
        return isAtCropStorage;
    }

    public Queue<Crop> getCrops() {
        return crops;
    }
}
