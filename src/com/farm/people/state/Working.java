// Working.java
package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Working implements State {
    @Override
    public void executeState(Worker worker) {
        worker.increaseTimesWorked();
        worker.decreaseHungerMeter(10);
        worker.decreaseSanityMeter(10);
        System.out.println(worker + " is working.");
    }

    @Override
    public void nextState(Worker worker) {
        if (worker.isWorkerOverWorked()) {
            worker.setState(new Eating());
        }
    }

    @Override
    public String toString() {
        return "Working";
    }
}
