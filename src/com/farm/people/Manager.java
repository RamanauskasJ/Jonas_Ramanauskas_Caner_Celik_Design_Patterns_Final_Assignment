package com.farm.people;

import com.farm.farm.Farm;
import com.farm.people.state.Working;
import com.farm.people.worker.Worker;

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

    public void motivateWorkers() {
        for (Farm farm : this.farms) {
            farm.removeOverWorkedWorkers();
            for (Worker worker : farm.getWorkers()) {
                worker.doTask();
                if (worker.getState() instanceof Working) {
                    worker.work(farm);
                }
            }
        }
    }
}
