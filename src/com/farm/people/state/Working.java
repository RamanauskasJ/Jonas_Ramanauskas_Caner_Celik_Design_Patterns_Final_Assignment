package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Working extends State {
    @Override
    public void nextState(Worker worker) {
        worker.setState(new Idle());
    }

    @Override
    public String toString() {
        return "Working";
    }


    @Override
    public void executeState(Worker worker) {
        worker.decreaseHungerMeter(10);
        worker.decreaseSanityMeter(20);
        worker.increaseTimesWorked();
    }
}
