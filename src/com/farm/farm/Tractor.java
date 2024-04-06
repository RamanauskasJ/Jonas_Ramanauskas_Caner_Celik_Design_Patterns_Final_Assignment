package com.farm.farm;

import com.farm.crop.Crop;
import java.util.ArrayDeque;
import java.util.Queue;

public class Tractor {
    private static final int MAX_NUMBER_OF_CROPS = 10;
    private final Queue<Crop> crops = new ArrayDeque<>();
    private boolean isAtCropStorage;

    public Tractor() {
        this.isAtCropStorage = false;
    }

    public boolean isAtCropStorage() {
        return isAtCropStorage;
    }

    public void setAtCropStorage(boolean atCropStorage) {
        isAtCropStorage = atCropStorage;
    }

    public boolean isFull() {
        return crops.size() >= MAX_NUMBER_OF_CROPS;
    }

    public Queue<Crop> getCrops() {
        return crops;
    }

    public void loadTractor(Crop crop) {
        if (!isFull()) {
            crops.add(crop);
        }
    }

    public void unloadTractor() {
        crops.forEach(CropStorage::addCrop);
        crops.clear();
    }

}
