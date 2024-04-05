package com.farm.people;

import com.farm.farm.Farm;

import java.util.HashSet;

public class Manager implements Subject {
    private static Manager managerSingleton;
    private final HashSet<Observer> observers = new HashSet<>();
    private final HashSet<Farm> farms = new HashSet<>();

    private Manager() {

    }

    public static Manager GetManager() {
        if (managerSingleton == null) {
            managerSingleton = new Manager();
        }

        return managerSingleton;
    }

    public void addFarm(Farm farm) {
        this.farms.add(farm);
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer :
                observers) {
            observer.Update();
        }
    }
}
