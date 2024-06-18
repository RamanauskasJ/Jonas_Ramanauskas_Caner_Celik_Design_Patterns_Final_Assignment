package com.farm.farm;

import com.farm.crop.Crop;
import com.farm.people.Manager;
import com.farm.people.worker.Worker;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class Farm {
    protected Queue<Crop> crops = new ArrayDeque<>();
    private final ArrayList<Worker> workers = new ArrayList<>();
    private final Queue<Crop> unloadedCrops = new ArrayDeque<>();
    private final Tractor tractor = new Tractor();
    private final int maxWorkers;

    public Farm(int maxWorkers) {
        Manager.GetManager().addFarm(this);
        this.maxWorkers = maxWorkers;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void addWorker(Worker worker) {
        if (this.workers.size() < maxWorkers) {
            this.workers.add(worker);
        }
    }

    public void harvestCrops() {
        Crop nextCrop = getNextCrop();

        if (nextCrop != null) {
            this.crops.remove(nextCrop);
            this.unloadedCrops.add(nextCrop);
        }
    }

    private Crop getNextCrop() {
        return this.crops.peek();
    }

    public Queue<Crop> getUnloadedCrops() {
        return unloadedCrops;
    }

    public Tractor getTractor() {
        return tractor;
    }

    public Queue<Crop> getCropsInTractor() {
        return tractor.getCrops(); // Assuming Tractor has a getCrops() method
    }

    public abstract Crop generateCrop();

    public void removeOverWorkedWorkers() {
        workers.removeIf(Worker::isWorkerOverWorked);
    }

}
