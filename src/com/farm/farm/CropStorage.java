package com.farm.farm;

import com.farm.crop.Crop;

import java.util.HashSet;

public class CropStorage {
    static HashSet<Crop> crops = new HashSet<>();

    public static HashSet<Crop> getCrops() {
        return crops;
    }

    public static void addCrop(Crop crop) {
        crops.add(crop);
    }
}
