// Eating.java
package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Eating implements State {
    @Override
    public void executeState(Worker worker) {
        worker.increaseHungerMeter(20);
        System.out.println(worker + " is eating.");
    }

    @Override
    public void nextState(Worker worker) {
        worker.setState(new Sleeping());
    }

    @Override
    public String toString() {
        return "Eating";
    }
}
