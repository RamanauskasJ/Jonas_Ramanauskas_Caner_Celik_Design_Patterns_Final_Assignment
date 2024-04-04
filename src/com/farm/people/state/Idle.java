package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Idle extends State {

    @Override
    public void nextState(Worker worker) {
        worker.setState(new Sleeping());
    }

    @Override
    public void executeState(Worker worker) {
        worker.decreaseSanityMeter(1);
        worker.decreaseHungerMeter(1);
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
