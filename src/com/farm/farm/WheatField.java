package com.farm.farm;

import com.farm.crop.CornCrop;
import com.farm.crop.Crop;
import com.farm.crop.OatCrop;
import com.farm.crop.WheatCrop;
import java.util.Random;

public class WheatField extends Farm {
    private static final int MAX_WORKERS = 15;

    public WheatField() {
        super(MAX_WORKERS);
        for (int i = 0; i < 1000; i++) {
            super.crops.add(GenerateCrop());
        }
    }

    @Override
    public Crop GenerateCrop() {
        Random random = new Random();
        int rand = random.nextInt(6); // Generates a random number between 0 (inclusive) and 6 (exclusive)
        double kg = random.nextDouble() * (1000 - 20) + 20; // Generates a random weight between 20 and 1000 kg

        return switch (rand) {
            case 0, 1, 2 -> new CornCrop(kg);
            case 3, 4 -> new WheatCrop(kg);
            case 5 -> new OatCrop(kg);
            default -> throw new RuntimeException("Defaulted exception");
        };
    }
}
