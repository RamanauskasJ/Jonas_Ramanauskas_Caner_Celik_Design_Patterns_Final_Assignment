package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Sleeping extends State {
    @Override
    public void nextState(Worker worker) {
        worker.setState(new Eating());
    }

    @Override
    public String toString() {
        return "Sleeping";
    }

    @Override
    public void executeState(Worker worker) {
        worker.resetTimesWorked();
        worker.decreaseHungerMeter(5);
        worker.increaseSanityMeter(20);
    }
}
