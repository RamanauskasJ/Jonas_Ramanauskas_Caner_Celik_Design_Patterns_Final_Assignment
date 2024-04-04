package com.farm.crop;

public abstract class Crop {
    protected double weightInKg;
    protected double pricePerKg;

    public Crop(double weightInKg, double price) {
        this.weightInKg = weightInKg;
        this.pricePerKg = price;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public double getPrice() {
        return pricePerKg * weightInKg;
    }

}
