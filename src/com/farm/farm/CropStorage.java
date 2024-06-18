package com.farm.farm;

import com.farm.crop.Crop;

import java.util.HashSet;
import java.util.Queue;

public class CropStorage {
    static HashSet<Crop> crops = new HashSet<>();

    public static HashSet<Crop> getCrops() {
        return crops;
    }

    public static void addCrops(Queue<Crop> newCrops) {
        crops.addAll(newCrops);
    }
}
