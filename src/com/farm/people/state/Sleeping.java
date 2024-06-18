// Sleeping.java
package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Sleeping implements State {
    @Override
    public void executeState(Worker worker) {
        worker.increaseSanityMeter(20);
        System.out.println(worker + " is sleeping.");
    }

    @Override
    public void nextState(Worker worker) {
        worker.setState(new Idle());
    }

    @Override
    public String toString() {
        return "Sleeping";
    }
}
